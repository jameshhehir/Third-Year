package PayrollSystem;
//Java core packages
import java.text.DecimalFormat;
import java.util.Date;

//Java extension packages
import javax.swing.JOptionPane;

public class Test {

 // test Employee hierarchy
 @SuppressWarnings("deprecation")
	public static void main(String args[]) {
     //Employee employee; // superclass reference
     String output = "";
     int numEmployees = 0; // int to represent the total number of valid employees
     Employee [] employees = new Employee [4]; 
     // array to hold at most 4 employees, for a more dynamic solution I would use an ArrayList for example. 
     
     //trying each type of employee
     try {
     	Boss boss = new Boss("John", "Smith", 800.0, new Date(103, 06, 15));
     	employees[numEmployees++] = boss;
     } catch (InvalidDate e) {
     	System.err.println(e);
     }
     
     try {
     	CommissionWorker commissionWorker = new CommissionWorker("Sue", "Jones", 400.0, 3.0, 150, new Date(16, 05, 15)); 
     	employees[numEmployees++] = commissionWorker;
     	// Depreciated so adds 1900 to 16 so the year is too low
     } catch (InvalidDate e) {
     	System.err.println(e);
     }
     
     try {
	        PieceWorker pieceWorker = new PieceWorker("Bob", "Lewis", 2.5, 200, new Date(1903, 2, 1)); 
	        employees[numEmployees++] = pieceWorker;
	      // 1903 + 1900 = 3803 so year is too high 
	    } catch (InvalidDate e) {
	    	System.err.println(e);
	    }
     
     try {
     	HourlyWorker hourlyWorker = new HourlyWorker("Karen", "Price", 13.75, 40, new Date(115, 11, 21));
     	employees[numEmployees++] = hourlyWorker;
     } catch (InvalidDate e) {
			System.err.println(e);
		}
     
     DecimalFormat precision2 = new DecimalFormat("0.00");
     float bonus;
     for(int i = 0; i < numEmployees; i++) {
     	bonus = 0;
     	
     	if ((new Date().getYear() - employees[i].getDate().getYear())  >= 5) {
     		// add bonus if they were employed 5+ years ago
     		bonus = 200; 
     	}
     	
     	
     	output += employees[i].toString() + " earned €"
     			+ precision2.format((employees[i].earnings() * (28.0 / 7)) + bonus) + "\n";
     	// assuming 28 days in a month so divide by 7 to get the amount per day, and multiplying by 28 to get
     	// the amount per month
     }
     
     JOptionPane.showMessageDialog(null, output, "Demonstrating Polymorphism", JOptionPane.INFORMATION_MESSAGE);

     System.exit(0);
 }
} // end class Test