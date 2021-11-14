import inheri.Employee;
import inheri.Manager;

public class App {
    public static void main(String[] args) {
        // 父类
        var link = new Employee("link");
        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        link.printEmployee();

        // 子类
        var boss = new Manager("luke", 80000, 2021, 12, 12);
        boss.setDesc("CTO");
        boss.setBonus(1000);
        boss.printEmployee();

        // 所有类的父对象
        var obj = new Employee("max", 800);
        obj.printEmployee();
    }
}
