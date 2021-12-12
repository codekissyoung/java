package Lexer;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer();
            var script = "int age = 45;";
            System.out.println("parse :" + script);
            var tokenReader = lexer.tokenize(script);
            dump(tokenReader);

            //测试inta的解析
            script = "inta age = 45;";
            System.out.println("\nparse :" + script);
            tokenReader = lexer.tokenize(script);
            dump(tokenReader);

            //测试in的解析
            script = "in age = 45;";
            System.out.println("\nparse :" + script);
            tokenReader = lexer.tokenize(script);
            dump(tokenReader);

            //测试>=的解析
            script = "age >= 45;";
            System.out.println("\nparse :" + script);
            tokenReader = lexer.tokenize(script);
            dump(tokenReader);

            //测试>的解析
            script = "age > 45;";
            System.out.println("\nparse :" + script);
            tokenReader = lexer.tokenize(script);
            dump(tokenReader);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 有限状态机的各种状态
    enum DfaState {
        Initial,    // 初始状态
        Identifier, // 标识符
        Assignment, // =
        GT,         // >
        GE,         // >=
        Plus,       // +
        Minus,      // -
        Star,       // *
        Slash,      // /
        If,
        Id_if1,
        Id_if2,
        Else,
        Id_else1,
        Id_else2,
        Id_else3,
        Id_else4,
        Int,
        Id_int1,
        Id_int2,
        Id_int3,
        SemiColon,
        LeftParen,
        RightParen,
        IntLiteral
    }

    // Token的一个简单实现只有类型和文本值两个属性
    private static final class SimpleToken implements Token {
        private TokenType type = null;         //Token类型
        private String text = null;         //文本值
        public TokenType getType() {return this.type;}
        public String getText() {
            return text;
        }
    }

    // 下面几个变量是在解析过程中用到的临时变量,如果要优化的话，可以塞到方法里隐藏起来
    private StringBuffer tokenText = null;   // 临时保存token的文本
    private List<Token> tokens = null;       // 保存解析出来的Token
    private SimpleToken token = null;        // 当前正在解析的Token

    private boolean isAlpha(int ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }
    private boolean isDigit(int ch) {
        return ch >= '0' && ch <= '9';
    }
    private boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    // 解析字符串，形成Token, 这是一个有限状态自动机，在不同的状态中迁移
    public SimpleTokenReader tokenize(String codeStr) throws IOException {
        var reader = new CharArrayReader(codeStr.toCharArray());
        tokens = new ArrayList<Token>();
        tokenText = new StringBuffer();
        token = new SimpleToken();
        int ich;
        char ch = 0;

        var state = DfaState.Initial; // 先待在初始化状态
        while ((ich = reader.read()) != -1) {
            // 读入了一个字符，接下来要开始生产 Token 了
            ch = (char)ich;
            switch (state) {
                // 如果是初始状态，则根据读入的字符发生跃迁
                case Initial:
                    state = initToken(ch);
                    break;
                // 如果已经是标识符状态
                case Identifier:
                    // 如果是字母或数字，则继续保持标识符状态
                    if (isAlpha(ch) || isDigit(ch)) {
                        tokenText.append(ch);
                    // 退出标识符状态，并保存Token
                    } else {
                        state = initToken(ch);
                    }
                    break;
                case GT: // > 状态
                    if (ch == '=') {
                        token.type = TokenType.GE;  //转换成GE
                        state = DfaState.GE;
                        tokenText.append(ch);
                    } else {
                        state = initToken(ch);      //退出GT状态，并保存Token
                    }
                    break;
                case IntLiteral:
                    // 继续保持在数字字面量状态
                    if (isDigit(ch)) {
                        tokenText.append(ch);
                    } else {
                        state = initToken(ch);
                    }
                    break;
                case GE:
                case Assignment:
                case Plus:
                case Minus:
                case Star:
                case Slash:
                case SemiColon:
                case LeftParen:
                case RightParen:
                    state = initToken(ch);
                    break;
                case Id_int1:
                    if (ch == 'n') {
                        state = DfaState.Id_int2;
                        tokenText.append(ch);
                    }
                    else if (isDigit(ch) || isAlpha(ch)){
                        state = DfaState.Identifier;
                        tokenText.append(ch);
                    }
                    else {
                        state = initToken(ch);
                    }
                    break;
                case Id_int2:
                    if (ch == 't') {
                        state = DfaState.Id_int3;
                        tokenText.append(ch);
                    }
                    else if (isDigit(ch) || isAlpha(ch)){
                        state = DfaState.Identifier;
                        tokenText.append(ch);
                    }
                    else {
                        state = initToken(ch);
                    }
                    break;
                case Id_int3:
                    if (isBlank(ch)) {
                        token.type = TokenType.Int;
                        state = initToken(ch);
                    }
                    else{
                        state = DfaState.Identifier;    //切换回Id状态
                        tokenText.append(ch);
                    }
                    break;
            }
        }
        // 把最后一个token送进去
        if (tokenText.length() > 0) {
            initToken(ch);
        }
        return new SimpleTokenReader(tokens);
    }

    /**
     * 有限状态机进入初始状态
     * 这个初始状态其实并不做停留，它马上进入其他状态
     * 开始解析的时候，进入初始状态；某个Token解析完毕，也进入初始状态，在这里把Token记下来，然后建立一个新的Token
     */
    private DfaState initToken(char ch) {
        if (tokenText.length() > 0) {
            token.text = tokenText.toString();
            tokens.add(token);
            tokenText = new StringBuffer();
            token = new SimpleToken();
        }

        DfaState newState;
        if (isAlpha(ch)) {
            if (ch == 'i') {
                newState = DfaState.Id_int1;
            } else {
                newState = DfaState.Identifier; //进入Id状态
            }
            token.type = TokenType.Identifier;
            tokenText.append(ch);
        } else if (isDigit(ch)) {
            newState = DfaState.IntLiteral;
            token.type = TokenType.IntLiteral;
            tokenText.append(ch);
        } else if (ch == '>') {
            newState = DfaState.GT;
            token.type = TokenType.GT;
            tokenText.append(ch);
        } else if (ch == '+') {
            newState = DfaState.Plus;
            token.type = TokenType.Plus;
            tokenText.append(ch);
        } else if (ch == '-') {
            newState = DfaState.Minus;
            token.type = TokenType.Minus;
            tokenText.append(ch);
        } else if (ch == '*') {
            newState = DfaState.Star;
            token.type = TokenType.Star;
            tokenText.append(ch);
        } else if (ch == '/') {
            newState = DfaState.Slash;
            token.type = TokenType.Slash;
            tokenText.append(ch);
        } else if (ch == ';') {
            newState = DfaState.SemiColon;
            token.type = TokenType.SemiColon;
            tokenText.append(ch);
        } else if (ch == '(') {
            newState = DfaState.LeftParen;
            token.type = TokenType.LeftParen;
            tokenText.append(ch);
        } else if (ch == ')') {
            newState = DfaState.RightParen;
            token.type = TokenType.RightParen;
            tokenText.append(ch);
        } else if (ch == '=') {
            newState = DfaState.Assignment;
            token.type = TokenType.Assignment;
            tokenText.append(ch);
        } else {
            newState = DfaState.Initial; // skip all unknown patterns
        }
        return newState;
    }

    // 打印所有的Token
    public static void dump(SimpleTokenReader tokenReader){
        System.out.println("text\ttype");
        Token token = null;
        while ((token= tokenReader.read())!=null){
            System.out.println(token.getText()+"\t\t"+token.getType());
        }
    }

    // 一个简单的Token流是把一个Token列表进行了封装
    private static class SimpleTokenReader implements TokenReader {
        List<Token> tokens;
        int pos = 0;

        public SimpleTokenReader(List<Token> tokens) {
            this.tokens = tokens;
        }

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
}