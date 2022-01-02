package Lexer;

public interface Token{
    TokenType getType(); // 类型
    String getText();    // 文本值
}