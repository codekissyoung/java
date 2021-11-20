package Lexer;

/**
 * 一个简单的Token。
 * 只有类型和文本值两个属性。
 */
public interface Token{
    TokenType getType();
    String getText();
}