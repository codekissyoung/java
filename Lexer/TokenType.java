package Lexer;

public enum TokenType{
    Plus,            // +
    Minus,           // -
    Star,            // *
    Slash,           // /
    doubleSlash,     // //
    GE,              // >=
    GT,              // >
    EQ,              // ==
    LE,              // <=
    LT,              // <
    SemiColon,       // ;
    singleQuotes,    // '
    doubleQuotes,    // "
    leftCurlyBrace,  // {
    rightCurlyBrace, // {
    leftParen,       // (
    rightParen,      // )
    Assign,          // =
    If,              // if
    Else,            // else
    Int,             // int
    Identifier,      // 标识符
    IntLiteral,      // 整型字面量
    StringLiteral    // 字符串字面量
}