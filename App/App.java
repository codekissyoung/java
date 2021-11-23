package App;
import inheri.Employee;
import inheri.Manager;
import javax.xml.XMLConstants;

public class App {
    public static void main(String[] args) {

        int x = 137;
        byte b = (byte) x;
        echo(String.valueOf(b));

        System.out.println("The square root " + "of \u03C0(π)\u2122 is " + Math.sqrt(Math.PI));

        var ems = new Employee[3];

        var link = new Employee("link");
        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        ems[0] = link;

        var boss = new Manager("luke", 80000, 2021, 12, 12);
        boss.setDesc("CTO");
        boss.setBonus(1000);
        ems[1] = boss;

        var max = new Employee("max", 800);
        ems[2] = max;

        for (var e: ems) {
            e.printEmployee();
        }

        var g = new App();
        echo(g.hello());

        var a = "hello";

        echo(a.toUpperCase());

        echo(a);

    }

    private String hello() {
        return XMLConstants.XML_NS_PREFIX;
    }

    public static void echo(String s){
        System.out.println(s);
    }

}
