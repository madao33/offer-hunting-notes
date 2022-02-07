package chap6.clone;

public class CloneTest {
    public static void main(String[] args)
    {
        try
        {
            Employee original = new Employee("madao", 40000);
            original.setHireDay(2023, 9, 1);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2024, 10, 1);
            System.out.println("original=" + original);
            System.out.println("copy=" + copy);
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
