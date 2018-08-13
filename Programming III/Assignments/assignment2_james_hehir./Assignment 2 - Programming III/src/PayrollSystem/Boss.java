package PayrollSystem;

import java.util.Date;

// Boss class derived from Employee.

public final class Boss extends Employee {

    private double weeklySalary;

    // constructor for class Boss
    public Boss(String first, String last, double salary, Date d) throws InvalidDate {
        super(first, last, d); // call superclass constructor
        setWeeklySalary(salary);
    }

    // set Boss's salary
    public void setWeeklySalary(double salary) {
        weeklySalary = (salary > 0 ? salary : 0);
    }

    // get Boss's pay
    public double earnings() {
        return weeklySalary;
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss