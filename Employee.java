import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Random;

public class Employee {

    // final 属性：被设置后，不能被修改，即不可以再指向别的String对象
    private final String name;

    // 显示指定初始值，是非常好的习惯
    private int id;
    private String designation = "";
    private double salary = 0.0;
    private int age = 18;
    private LocalDate hireDay = LocalDate.now();

    private static int nextId;

    // 类第一次加载的时候，将会进行静态域的初始化
    static {
        // 给 nextId 一个随机的初始值
        Random randomIntGen = new Random();
        nextId = randomIntGen.nextInt(10000);
    }

    // new 对象时，初始化块
    {
        id = nextId;
        nextId ++;
    }

    public Employee(){
        name = "";
        salary = 0;
        hireDay = LocalDate.now();
    }

    // constructor 1
    public Employee(String name) {
        this.name = name;
    }

    // constructor 2
    public Employee(String name,double s ) {
        this(name); // 调用另一个 constructor
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

    // 静态的main方法用于单元测试 运行: java Employee
    public static void main(String[] args)
    {
        Employee link = new Employee("link");
        Employee max = new Employee("max",700);

        link.setAge(26);
        link.setDesc("程序员");
        link.empSalary(1000);
        link.setHireDay(2021, 1,20);

        max.setAge(21);
        max.setDesc("程序员");
        max.raiseSalary();

        Employee[] emps = new Employee[2];
        emps[0] = link;
        emps[1] = max;

        for(Employee e : emps){
            e.printEmployee();
        }

        // 使用静态方法实现工厂方法，每次调用都返回一个新 NumberFormat 对象
        NumberFormat cur = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(cur.format(x)); // ￥0.10
        System.out.println(percent.format(x)); // 10%
    }
}
