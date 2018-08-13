package PayrollSystem;

import java.util.Date;

@SuppressWarnings("serial")
public class InvalidDate extends Exception {

	public InvalidDate(String name, Date wrongDate) {
		
		super("Name: " + name + "\tEntered Date: " 
				+ wrongDate + "\nDate must be at least in 1990 or above but not in the furture");
	
	}

}