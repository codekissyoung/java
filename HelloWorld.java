import static java.util.stream.IntStream.iterate;

public class HelloWorld
{
    public static final String p = "="; // 类常量

    public static void main(String[] args) {
        String greeting = "Welcome to Java";

        System.out.println(greeting);

        // 迭代器
        iterate(greeting.length() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(i -> p)
                .forEach(System.out::print);

        System.out.println();

        // 字符串 拼接 +
        System.out.println("The square root " + "of \u03C0(π)\u2122 is " + Math.sqrt(Math.PI));

        // 子串
        String fullStr = "Hello";
        String subString = fullStr.substring(0, 3);
        System.out.println("Sub String : " + subString); // Sub String : Hel

        String changStr = "Hello link";
        changStr = changStr.substring(0, 5) + " max";
        System.out.println("changStr : " + changStr); // changStr : Hello max

        String str = null;
        String str2 = "";
        if (str != null) {
            System.out.println("str is not null !");
        }
//        if (!str.equals("")) { // 可能发生空对象调用异常 !!!
//            System.out.println("str is not empty !");
//        }
        // 正确判断方式
        if (str != null && !str.equals("")) {
            System.out.println("str is not empty !");
        }

    }
}
