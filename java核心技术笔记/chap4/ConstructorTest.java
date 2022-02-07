package chap4;

import java.util.*;

public class ConstructorTest{
    public static void main(String[] args)
    {
        Employee1[] staff = new Employee1[3];

        staff[0] = new Employee1("Harry", 4000);
        staff[1] = new Employee1(60000);
        staff[2] = new Employee1();

        for(Employee1 e : staff)
            System.out.println("name = " + e.getName() + ", id = " + e.getId() + ", salary = " + e.getSalary());
    }
    
}

class Employee1
{
    /**
     * The "Hearts" card suit
     */
    private static int nextId;

    private int id;
    private String name = "";
    private double salary;

    static
    {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    {
        id = nextId;
        nextId++;
    }

    public Employee1(String n, double s)
    {
        name = n;
        salary = s;
    }

    public Employee1(double s)
    {
        this("Employee #" + nextId, s);
    }

    public Employee1()
    {

    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public int getId()
    {
        return id;
    }
}
