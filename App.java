import inheritance.Employee;

public class App
{
    public static void main(String[] args)
    {
        Employee link = new Employee("link");

        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        link.setHireDay(2021, 1,20);

        link.printEmployee();
    }
}
