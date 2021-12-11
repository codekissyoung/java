package Lexer;

// 一个简单的 Token
public interface Token{
    TokenType getType(); // 类型
    String getText();    // 文本值
}