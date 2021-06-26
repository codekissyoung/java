package inheritance;

import com.sun.nio.sctp.NotificationHandler;

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
