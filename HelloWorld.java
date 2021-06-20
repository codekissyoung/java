import java.util.Arrays;

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

        String strLen = "Hello 彦！π ™ ";
        System.out.println("UTF-16 save length : " + strLen.length()); // 12
        System.out.println("CodePoint length : " + strLen.codePointCount(0,strLen.length())); // 12
        System.out.println("chaAt : " + strLen.charAt(10)); // ™

        // 转换成 Unicode 码数组
        int[] codePoints = strLen.codePoints().toArray();
        System.out.println(Arrays.toString(codePoints)); // 72, 101, 108, 108, 111, 32 ...

        // 赋值，其实是多了一个引用
        int[] luckyNumber = {43,31,44,23,55};
        int[] fackCopy = luckyNumber;
        fackCopy[0] = 100;
        System.out.println(Arrays.toString(luckyNumber)); // [100, 31, 44, 23, 55]

        // 深拷贝，是重新申请了一块内存空间，然后赋值过去，再绑定到变量名上
        int[] deepCopy = Arrays.copyOf(luckyNumber, luckyNumber.length);
        deepCopy[2] = 100;
        System.out.println(Arrays.toString(luckyNumber)); // [100, 31, 44, 23, 55]
    }
}
