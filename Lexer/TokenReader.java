package Lexer;

// 一个Token流。由Lexer生成。Parser可以从中获取Token
public interface TokenReader{
    // 返回Token流中下一个Token，并从流中取出。 如果流已经为空，返回null
    public Token read();
    public Token peek();
    public void unread();                  // Token流回退一步。恢复原来的Token
    public int getPosition();              // 获取Token流当前的读取位置
    public void setPosition(int position); // 设置Token流当前的读取位置
}