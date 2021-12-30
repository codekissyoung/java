package Lexer;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public static void main(String[] args) {
        try {
            var lexer = new Lexer();
            var script = """
                    int age = 45;
                    if(age >= 76) {
                        println('>');
                    }
                    int level += 2 + 3 * 5; // test 注释;
                    var name = 'link';
                    """;
            System.out.println("parse :\n" + script);
            var tokenReader = lexer.tokenize(script);
            dump(tokenReader);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 有限状态机的各种状态
    enum fsm_state {
        Init,       // 初始状态
        Identifier, // 标识符
        Assign,     // =
        GT,         // >
        GE,         // >=
        Plus,       // +
        Minus,      // -
        Star,       // *
        Slash,      // /
        doubleSlash,// //
        Id_int1,
        Id_int2,
        Id_int3,
        SemiColon,
        LeftParen, // (
        RightParen, // )
        singleQuotes, // '
        doubleQuotes, // "
        leftCurlyBrace, // {
        rightCurlyBrace, // {
        IntLiteral,    // Int 字面量
        StringLiteral, // String 字面量
    }

    // Token的一个简单实现只有类型和文本值两个属性
    private static final class SimpleToken implements Token {
        private TokenType type = null;         //Token类型
        private String text = null;         //文本值
        public TokenType getType() {return this.type;}
        public String getText() {return this.text;}
    }

    // 一个简单的Token流是把一个Token列表进行了封装
    private static class SimpleTokenReader implements TokenReader {
        List<Token> tokens;
        int pos = 0;
        public SimpleTokenReader(List<Token> tokens) {this.tokens = tokens;}
        public Token read() {
            if (pos < tokens.size()) {
                return tokens.get(pos++);
            }
            return null;
        }
        public Token peek() {
            if (pos < tokens.size()) {
                return tokens.get(pos);
            }
            return null;
        }
        public void unread() {
            if (pos > 0) {
                pos--;
            }
        }
        public int getPosition() {
            return pos;
        }
        public void setPosition(int position) {
            if (position >=0 && position < tokens.size()){
                pos = position;
            }
        }
    }

    // 下面几个变量是在解析过程中用到的临时变量,如果要优化的话，可以塞到方法里隐藏起来
    private StringBuffer tokenTmpStr = null;   // 临时保存token的文本
    private SimpleToken oneToken  = null;       // 当前正在解析的Token
    private List<Token> tokenList = null;      // 保存解析出来的Token

    // 解析字符串，形成Token, 这是一个有限状态自动机，在不同的状态中迁移
    public SimpleTokenReader tokenize(String codeStr) throws IOException {
        var reader = new CharArrayReader(codeStr.toCharArray());
        tokenList = new ArrayList<>();
        tokenTmpStr = new StringBuffer();
        oneToken = new SimpleToken();
        int ich = 0;
        char ch = 0;

        var state = fsm_state.Init; // 先待在初始化状态
        while (( ich = reader.read() ) != -1)
        {
            ch = (char)ich; // 读入了一个字符，接下来要开始生产 Token 了
            switch (state) // 状态机当前状态
            {
                case Init:
                case GE: // >=
                case Assign:
                case Plus:
                case Minus:
                case Star:
                case SemiColon:
                case LeftParen:
                case RightParen:
                case singleQuotes:
                case doubleQuotes:
                case leftCurlyBrace:
                case rightCurlyBrace:
                    state = changeState(ch);// 根据读入的 ch , 修改状态
                    break;
                    // // 后面的字符都被解析为注释
                case doubleSlash:
                    if(ch != '\n'){
                        tokenTmpStr.append(ch);
                    }else{
                        state = changeState(ch);
                    }
                    break;
                case Identifier: // 如果已经是标识符状态
                    if (isAlpha(ch) || isDigit(ch)) { // 如果是字母或数字，则继续保持标识符状态
                        tokenTmpStr.append(ch);
                    } else {
                        state = changeState(ch);
                    }
                    break;
                case GT: // > 状态
                    if (ch == '=') {
                        oneToken.type = TokenType.GE;  //转换成GE
                        state = fsm_state.GE;
                        tokenTmpStr.append(ch);
                    } else {
                        state = changeState(ch);
                    }
                    break;
                case Slash:
                    if(ch == '/'){  // 当前是 / , 再来一个 /
                        oneToken.type = TokenType.doubleSlash;
                        state = fsm_state.doubleSlash;
                        tokenTmpStr.append(ch);
                    }else{
                        state = changeState(ch);
                    }
                    break;
                case IntLiteral:
                    if (isDigit(ch)) {
                        tokenTmpStr.append(ch);
                    } else {
                        state = changeState(ch);
                    }
                    break;
                case Id_int1:
                    if (ch == 'n') {
                        state = fsm_state.Id_int2;
                        tokenTmpStr.append(ch);
                    } else if (isDigit(ch) || isAlpha(ch)){
                        state = fsm_state.Identifier;
                        tokenTmpStr.append(ch);
                    } else {
                        state = changeState(ch);
                    }
                    break;
                case Id_int2:
                    if (ch == 't') {
                        state = fsm_state.Id_int3;
                        tokenTmpStr.append(ch);
                    } else if (isDigit(ch) || isAlpha(ch)){
                        state = fsm_state.Identifier;
                        tokenTmpStr.append(ch);
                    } else {
                        state = changeState(ch);
                    }
                    break;
                case Id_int3:
                    if (isBlank(ch)) {
                        oneToken.type = TokenType.Int;
                        state = changeState(ch);
                    } else{
                        state = fsm_state.Identifier;    //切换回Id状态
                        tokenTmpStr.append(ch);
                    }
                    break;
            }
        }

        // 文件已经读到末尾，直接将 tokenTmpStr 作为 token，加入到序列
        if (tokenTmpStr.length() > 0) {
            changeState(ch);
        }

        return new SimpleTokenReader(tokenList);
    }

    /**
     * 有限状态机进入初始状态
     * 这个初始状态其实并不做停留，它马上进入其他状态
     * 开始解析的时候，进入初始状态；某个Token解析完毕，
     * 也进入初始状态，在这里把Token记下来，然后建立一个新的Token
     */
    private fsm_state changeState(char ch) {
        // 已经鉴定了一个 token 了, 加入到序列中
        if (tokenTmpStr.length() > 0) {
            oneToken.text = tokenTmpStr.toString();
            tokenList.add(oneToken);
            tokenTmpStr = new StringBuffer();
            oneToken = new SimpleToken();
        }
        fsm_state newState;
        if (isAlpha(ch)) {
            if (ch == 'i') {
                newState = fsm_state.Id_int1;
            } else {
                newState = fsm_state.Identifier; // 进入Id状态
            }
            oneToken.type = TokenType.Identifier;
            tokenTmpStr.append(ch);
        } else if (isDigit(ch)) {
            newState = fsm_state.IntLiteral;
            oneToken.type = TokenType.IntLiteral;
            tokenTmpStr.append(ch);
        } else if (ch == '>') {
            newState = fsm_state.GT;
            oneToken.type = TokenType.GT;
            tokenTmpStr.append(ch);
        } else if (ch == '+') {
            newState = fsm_state.Plus;
            oneToken.type = TokenType.Plus;
            tokenTmpStr.append(ch);
        } else if (ch == '-') {
            newState = fsm_state.Minus;
            oneToken.type = TokenType.Minus;
            tokenTmpStr.append(ch);
        } else if (ch == '*') {
            newState = fsm_state.Star;
            oneToken.type = TokenType.Star;
            tokenTmpStr.append(ch);
        } else if (ch == '/') {
            newState = fsm_state.Slash;
            oneToken.type = TokenType.Slash;
            tokenTmpStr.append(ch);
        } else if (ch == ';') {
            newState = fsm_state.SemiColon;
            oneToken.type = TokenType.SemiColon;
            tokenTmpStr.append(ch);
        } else if (ch == '(') {
            newState = fsm_state.LeftParen;
            oneToken.type = TokenType.leftParen;
            tokenTmpStr.append(ch);
        } else if (ch == ')') {
            newState = fsm_state.RightParen;
            oneToken.type = TokenType.rightParen;
            tokenTmpStr.append(ch);
        } else if (ch == '=') {
            newState = fsm_state.Assign;
            oneToken.type = TokenType.Assign;
            tokenTmpStr.append(ch);
        } else if(ch == '\''){
            newState = fsm_state.singleQuotes;
            oneToken.type = TokenType.singleQuotes;
            tokenTmpStr.append(ch);
        } else if(ch == '\"'){
            newState = fsm_state.doubleQuotes;
            oneToken.type = TokenType.doubleQuotes;
            tokenTmpStr.append(ch);
        } else if (ch =='{'){
            newState = fsm_state.leftCurlyBrace;
            oneToken.type = TokenType.leftCurlyBrace;
            tokenTmpStr.append(ch);
        } else if (ch =='}'){
            newState = fsm_state.rightCurlyBrace;
            oneToken.type = TokenType.rightCurlyBrace;
            tokenTmpStr.append(ch);
        } else {
            if( isBlank(ch)){
                String str = "";
                if(ch == ' '){
                    str = "\\b";
                }else if(ch == '\t'){
                    str = "\\t";
                }else{
                    str = "\\n";
                }
//                System.out.printf("skipped : %d "+str+"\n",(int)ch);
            } else{
                System.out.printf("skipped unknown character: %d %c\n",(int)ch, ch);
            }
            newState = fsm_state.Init;
        }
        return newState;
    }

    // 打印所有的Token
    public static void dump(SimpleTokenReader tokenReader){
        System.out.println("text\t\t\type\n------------------------");
        Token token = null;
        while ((token = tokenReader.read())!=null){
            System.out.println(token.getText()+"\t\t\t"+token.getType());
        }
    }

    private boolean isAlpha(int ch) {return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';}
    private boolean isDigit(int ch) {return ch >= '0' && ch <= '9';}
    private boolean isBlank(int ch) {return ch == ' ' || ch == '\t' || ch == '\n';}
}