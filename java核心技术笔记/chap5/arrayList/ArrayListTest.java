package chap5.arrayList;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args)
    {
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Madao33", 20000, 1997, 6, 8));
        staff.add(new Employee("TheXun", 18000, 1996, 9, 18));
        staff.add(new Employee("MianBaoShu", 20020, 1996, 10, 10));

        for(Employee e : staff)
            e.raiseSalary(5);

        for(Employee e : staff)
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
    }
    
}
