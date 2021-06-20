import java.util.Date;

public class OOP
{
    public static void main(String[] args)
    {
        Date birthday = new Date(); // 一个Date对象
        System.out.println(birthday.toString());

        Date deadline = birthday;
        System.out.println(deadline.toString());

    }
}
