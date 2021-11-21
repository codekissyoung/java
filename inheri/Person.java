package inheri;

public abstract class Person
{
    protected String name;
    protected int age;
    public Person(String name)
    {
        this.name = name;
    }
    public abstract void run();
    public abstract String getName();
    public abstract String getDesc();
}
