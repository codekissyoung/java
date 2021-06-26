package inheritance;

public class Manager extends Employee{

    private double bonus;

    public Manager(String name, double salary, int year, int month, int day)
    {
        super(name, salary, year, month, day);
        this.bonus = 0;
    }

    public double getSalary()
    {
        return super.getSalary() + this.bonus;
    }

    public void setBonus(double b)
    {
        this.bonus = b;
    }

    // equals 的实现要点：
    // 1. x.equals(x) 必须返回 true
    // 2. x.equals(y) 和 y.equals(x) 必须拥有相同的结果！！！
    // 3. 如果 x.equals(y) 且 y.equals(z) ，则可以推出 x.equals(z)
    // 4. x.equals(null) 返回 false
    public boolean equals(Object other)
    {
        if(!super.equals(other))
            return false;
        if(!(other instanceof Object))
            return false;
        Manager o = (Manager) other;
        return this.bonus == o.bonus;
    }

}
