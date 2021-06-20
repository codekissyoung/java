import java.time.LocalDate;

public class Employee {
    private final String name;
    private String designation;
    private double salary;
    private int age;
    private LocalDate hireDay;

    // constructor 1

    //    public Employee(String name, double s){
//        this.name = name;
//        this.salary = s;
//    }
    public Employee(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesc(String desc) {
        designation = desc;
    }

    public void empSalary(double s) {
        salary = s;
    }

    public void raiseSalary() {
        this.salary += 100;
    }

    public void setHireDay(int year, int month, int day) {
        this.hireDay = LocalDate.of(year, month, day);
    }

    public void printEmployee() {
        System.out.println("名字:" + name);
        System.out.println("年龄:" + age);
        System.out.println("职位:" + designation);
        System.out.println("薪水:" + salary);
    }
}
