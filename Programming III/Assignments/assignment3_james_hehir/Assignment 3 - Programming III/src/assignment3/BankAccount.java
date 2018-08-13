//James Hehir 13318731

package assignment3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
	private String name;
	private static long generateAccountNo = 12123124;
	private long accountNo;
	private double balance = 0;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	private transient int overdraft;
	
	public BankAccount(String name,String date,double amount){
		this.name = name;
		transactions.add(new Transaction(date,"Open Account",amount));
		balance+=amount;
		accountNo = generateAccountNo;
		generateAccountNo++;
		overdraft = 100;
	}
	
	public String getTransactionDetail() {
		String output = "Transaction details :\n";
		int i = 1;
		for (int j = 0; j < transactions.size(); j++) {
			Transaction t = transactions.get(j);
			output+= i + "."+t.toString()+"\n";
			i++;
		}
		return output;
	}
	
	public void withdraw(String date, int amount) {
		String type = "Withdraw";

		if(balance - amount >= 0) {
		balance-=amount;
		transactions.add(new Transaction(date,type,amount));
		} else
			System.out.println("Unable to withdraw "+ amount);
	}
	
	public void deposit(String date, double amount) {
		String type = "Deposit";
		balance+=amount;
		transactions.add(new Transaction(date,type,amount));
	}
	
	public String toString() {
		
		String output = "Account No.:" + accountNo + "\n" +
               "Name: " + name + "\n" +
			   "Balance: " + balance + "\n";
		
		switch (overdraft) {
		case 0:
			output += "Overdraft: Not available";
			break;
		default:
			output+="Overdraft: "+overdraft;
			break;
		}
		
		return output;
	}
	
}