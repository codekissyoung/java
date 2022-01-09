package Demo.codePoint;
public class CharCodePointDemo {
    private static String str1 = "hi𝕆";

    public static void main(String[] args) {
        positiveOrderTraversalAllCodePoint(str1);
        negativeOrderTraversalAllCodePoint(str1);
        intStreamTraversalAllCodePoint(str1);
    }

    // 正序遍历字符串的所有码点
    public static void positiveOrderTraversalAllCodePoint(String sentence) {
        System.out.print(sentence + " length is " + sentence.length() + ".  ");
        for (int index = 0; index < sentence.length(); ) {
            int cp = sentence.codePointAt(index);
            System.out.print(cp + " ");
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
        System.out.print(sentence + " length is " + sentence.length() + ".  ");
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
    public static void intStreamTraversalAllCodePoint(String sentence) {
        System.out.print(sentence + " length is " + sentence.length() + ".  ");

        int[] codePoints = sentence.codePoints().toArray();

        for (int ele : codePoints) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}
