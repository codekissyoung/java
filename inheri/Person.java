package inheri;

public abstract class Person
{
    protected String name;

    public Person(String name)
    {
        this.name = name;
    }

    public abstract void run();
    public abstract String getName();
    public abstract String getDesc();



}
