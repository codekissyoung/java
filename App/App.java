package App;

import inheri.Employee;
import inheri.Manager;
import javax.xml.XMLConstants;

public class App {
    public static void main(String[] args) {

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
        System.out.println(g.hello());
    }

    private String hello() {
        return XMLConstants.XML_NS_PREFIX;
    }
}
