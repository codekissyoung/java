import static java.util.stream.IntStream.iterate;

public class HelloWorld
{
    public static final String p = "="; // 类常量

    public static void main(String[] args)
    {
        String greeting = "Welcome to Java";

        System.out.println(greeting);

        // 迭代器
        iterate(greeting.length() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(i -> p)
                .forEach(System.out::print);

        System.out.println();

        // 字符串 拼接 +
        System.out.println("The square root " + "of \u03C0(π)\u2122 is " + Math.sqrt(Math.PI));

    }

}
