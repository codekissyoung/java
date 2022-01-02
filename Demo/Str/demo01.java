package Demo.Str;

// 将一个字符串中的空格替换成 "%20"
public class demo01 {
    public static void main(String[] args) {
        System.out.println(replaceSpace("a b c"));
        System.out.println(replaceSpace(" "));
    }

    public static String replaceSpace(String s) {
        if (s == null) {
            return null;
        }
        var str = new StringBuilder(s);
        int length = str.length();
        int spaceNum = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int oldStr = length - 1;
        length += 2 * spaceNum;
        int newStr = length - 1;
        str.setLength(length);
        while (spaceNum > 0 && newStr >= 0) {
            char ch = str.charAt(oldStr--);
            if (ch == ' ') {
                str.setCharAt(newStr--, '0');
                str.setCharAt(newStr--, '2');
                str.setCharAt(newStr--, '%');
                spaceNum--;
            } else {
                str.setCharAt(newStr--, ch);
            }
        }
        return str.toString();
    }
}
