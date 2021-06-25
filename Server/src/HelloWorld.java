package Server.src;

import java.text.NumberFormat;

public class HelloWorld {

    public static void main(String[] args) {

        Server.codekissyoung.Employee link = new Server.codekissyoung.Employee("link");
        Server.codekissyoung.Employee max = new Server.codekissyoung.Employee("max",700);

        link.setAge(26);
        link.setDesc("程序员aaa");
        link.empSalary(1000);
        link.setHireDay(2021, 1,20);

        max.setAge(21);
        max.setDesc("程序员");
        max.raiseSalary();

        Server.codekissyoung.Employee[] emps = new Server.codekissyoung.Employee[2];
        emps[0] = link;
        emps[1] = max;

        for(Server.codekissyoung.Employee e : emps){
            e.printEmployee();
        }

        // 使用静态方法实现工厂方法，每次调用都返回一个新 NumberFormat 对象
        NumberFormat cur = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(cur.format(x)); // ￥0.10
        System.out.println(percent.format(x)); // 10%


        String greeting = "Welcome to Java";

        System.out.println(greeting);

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

        String strLen = "Hello 彦！π ™ ";
        System.out.println("UTF-16 save length : " + strLen.length()); // 12
        System.out.println("CodePoint length : " + strLen.codePointCount(0,strLen.length())); // 12
        System.out.println("chaAt : " + strLen.charAt(10)); // ™
    }
}
