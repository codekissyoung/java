package Demo.codePoint;

import java.util.Arrays;

public class CharCodePointDemo {

    // 虽然只有三个 unicode 字符串
    // 但使用了 4 个 char 去存储(UTF-16编码)
    // str1.length() 为 4
    private static final String str1 = "h𝕆𝕆3";

    public static void main(String[] args) {
        System.out.println(str1.length()); // 6 存储使用的 char 个数
        System.out.println(str1.codePointCount(0, str1.length())); // 4 实际码点的个数
        var index = str1.offsetByCodePoints(0,2); // 3
        System.out.println(index); // 3
        System.out.println(str1.codePointAt(index)); // 120134 𝕆的Unicode编码值

        // 转换成 Unicode 码数组
        var codePoints = str1.codePoints().toArray();
        System.out.println(Arrays.toString(codePoints)); // [104, 120134, 120134, 51]

        var str2 = new String(codePoints, 0, codePoints.length);
        System.out.println(str2); // h𝕆𝕆3

        positiveOrderTraversalAllCodePoint(str1);
        negativeOrderTraversalAllCodePoint(str1);
        intStreamTraversalAllCodePoint(str1);
    }

    // 正序遍历字符串的所有码点
    public static void positiveOrderTraversalAllCodePoint(String sentence) {
        for (int index = 0; index < sentence.length(); ) {
            int cp = sentence.codePointAt(index);
            System.out.print(cp + " ");
            // 如果是两个码点存储的 Unicode
            if (Character.isSupplementaryCodePoint(cp)) {
                index += 2;
            } else {
                index++;
            }
        }
        System.out.println();
    }

    // 逆序遍历字符串的所有码点
    public static void negativeOrderTraversalAllCodePoint(String sentence) {
        int index = sentence.length();
        while (--index >= 0) {
            if (Character.isSurrogate(sentence.charAt(index))) {
                index --;
            }
            System.out.print(sentence.codePointAt(index) + " ");
        }
        System.out.println();
    }

    // int流-遍历字符串的所有码点
    public static void intStreamTraversalAllCodePoint(String str) {
        int[] codePoints = str.codePoints().toArray();
        for (int ele : codePoints) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}
