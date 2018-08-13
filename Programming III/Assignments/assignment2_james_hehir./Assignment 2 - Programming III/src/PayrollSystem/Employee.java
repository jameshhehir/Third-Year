package PayrollSystem;

import java.util.Date;

//Abstract base class Employee.

public abstract class Employee {

 private String firstName;
 private String lastName;
 private Date joinDate;
 private static int globalAccNum = 1;
 private int accNum;
 

 // constructor
 @SuppressWarnings("deprecation")
	public Employee(String first, String last, Date d) throws InvalidDate {
 	joinDate = d;
 	firstName = first;
     lastName = last;
 	
 	while(joinDate.after(new Date()) || joinDate.before(new Date(90, 0, 1))) {
 		throw new InvalidDate(this.toString(), joinDate);
 	}
 	
 	

     accNum = globalAccNum++;
 }

 // get first name
 public String getFirstName() {
     return firstName;
 }

 // get last name
 public String getLastName() {
     return lastName;
 }
 
 public Date getDate() {
 	return joinDate;
 }

 public String toString() {
     return  firstName + ' ' + lastName + ' ' + accNum;
 }

 public abstract double earnings();
}