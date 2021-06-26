import inheritance.Employee;
import inheritance.Manager;

public class App
{
    public static void main(String[] args)
    {
        // 父类
        Employee link = new Employee("link");
        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        link.printEmployee();

        // 子类
        Manager boss = new Manager("luke", 80000, 2021, 12,12);
        boss.setDesc("CTO");
        boss.setBonus(1000);
        boss.printEmployee();
    }
}
