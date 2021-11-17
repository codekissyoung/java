import inheri.Employee;
import inheri.Manager;

public class App {
    public static void main(String[] args) {
        var ems = new Employee[3];

        // 父类
        var link = new Employee("link");
        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        ems[0] = link;

        // 子类
        var boss = new Manager("luke", 80000, 2021, 12, 12);
        boss.setDesc("CTO");
        boss.setBonus(1000);
        ems[1] = boss;

        // 所有类的父对象
        var max = new Employee("max", 800);
        ems[2] = max;

        for (Employee e: ems) {
            e.printEmployee();
        }
    }
}
