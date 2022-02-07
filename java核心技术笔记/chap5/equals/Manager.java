package chap5.equals;

public class Manager extends Employee{
    
    private double bonus;

    /**
     * Manager Class which extends from Employee
     * @param name the employee's name
     * @param salary the salary
     * @param year the hire year
     * @param month the hire month
     * @param day the hire day
     */
    public Manager(String name, double salary, int year, int month, int day)
    {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary()
    {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double bonus)
    {
        this.bonus = bonus;
    }

    public boolean equals(Object otherObject)
    {
        if(!super.equals(otherObject)) return false;
        Manager other = (Manager) otherObject;
        // super.equals checked that this and other belong to the same class
        return bonus == other.bonus;
    }

    public int hashCode()
    {
        return super.hashCode() + 17 * new Double(bonus).hashCode();
    }

    public String toString()
    {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}

