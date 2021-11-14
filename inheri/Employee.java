package inheri;

import java.time.LocalDate;
import java.util.Random;
import static java.lang.System.out;

public class Employee extends Person {

    private String designation = "";
    private double salary = 0.0;
    private int age = 0;
    private final LocalDate hireDay; // final 属性：被设置后，不能被修改，即不可以再指向别的String对象

    // 类第一次加载的时候，将会进行静态域的初始化
    private static int nextId;

    static {
        Random randomIntGen = new Random();         // 给 nextId 一个随机的初始值
        nextId = randomIntGen.nextInt(10000);
    }

    // new 对象时，初始化块
    private final int id;

    {
        this.id = nextId;
        nextId++;
    }

    public Employee() {
        super("");
        salary = 0;
        hireDay = LocalDate.now();
    }

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public Employee(String name) {
        super(name);
        this.hireDay = LocalDate.now();
    }

    public Employee(String name, double salary) {
        this(name); // 调用另一个 constructor
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return this.designation;
    }

    public void setDesc(String desc) {
        designation = desc;
    }

    public void empSalary(double s) {
        salary = s;
    }

    @Override
    public void run() {
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void raiseSalary() {
        this.salary += 100;
    }

    public double getSalary() {
        return this.salary;
    }

    // 错误示例: 直接给对象出去，破坏了封装性
    public LocalDate getHireDate() {
        return this.hireDay;
    }

//    public LocalDate getHireDay() {
//        return (LocalDate) this.hireDay.clone(); // 给出一个拷贝版本
//    }

    // 类方法可以访问类私有属性（包括使用本类生成的别的对象的私有属性）
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        if (!(other instanceof Employee))
            return false;
        if (this == other)
            return true;
        Employee e = (Employee) other;

        // e 竟然可以调用私有成员
        return this.name.equals(e.name) && this.hireDay.equals(e.hireDay);
    }

    public void printEmployee() {
        out.println("Id : " + this.id);
        out.println("名字:" + name);
        out.println("年龄:" + age);
        out.println("职位:" + this.getDesc());
        out.println("薪水:" + salary);
        out.println("-----------------------------------------------------------------");
    }

}
