import java.time.LocalDate;

public class Employee {

    // final 属性：被设置后，不能被修改，即不可以再指向别的String对象
    private final String name;

    private String designation;
    private double salary;
    private int age;
    private LocalDate hireDay;

    // constructor 1
    public Employee(String name) {
        this.name = name;
    }

    // constructor 2
    public Employee(String name,double s ) {
        this.name = name;
        this.salary = s;
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

    public LocalDate getHireDate()
    {
        return this.hireDay; // 直接给对象出去，破坏了封装性
    }

//    public LocalDate getHireDay() {
//        return (LocalDate) this.hireDay.clone(); // 给出一个拷贝版本
//    }

    // 类方法可以访问类私有属性（包括使用本类生成的别的对象的私有属性）
    public boolean equals(Employee other)
    {
        return this.name.equals(other.name); // other 竟然可以调用私有成员?!
    }

    public void printEmployee() {
        System.out.println("名字:" + name);
        System.out.println("年龄:" + age);
        System.out.println("职位:" + designation);
        System.out.println("薪水:" + salary);
    }
}
