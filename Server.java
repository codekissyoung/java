import com.codekissyoung.Employee;
import java.text.NumberFormat;

public class Server {

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
