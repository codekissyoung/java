public class EmployeeTest
{
    public static void main(String[] args)
    {
        Employee empOne = new Employee("link");
        Employee empTwo = new Employee("max");

        empOne.empAge(26);
        empOne.empDesignation("高级程序员");
        empOne.empSalary(1000);
        empOne.printEmployee();

        System.out.println();

        empTwo.empAge(21);
        empTwo.empDesignation("菜鸟程序员");
        empTwo.empSalary(500);
        empTwo.printEmployee();
    }
}
