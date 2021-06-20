public class EmployeeRun
{
    public static void main(String[] args)
    {
        Employee empOne = new Employee("link");

        empOne.setAge(26);
        empOne.setDesc("程序员");
        empOne.empSalary(1000);
        empOne.setHireDay(2021, 1,20);

        Employee empTwo = new Employee("max",700);
        empTwo.setAge(21);
        empTwo.setDesc("程序员");
        empTwo.raiseSalary();

        Employee[] emps = new Employee[2];
        emps[0] = empOne;
        emps[1] = empTwo;

        for(Employee e : emps){
            e.printEmployee();
        }



    }
}
