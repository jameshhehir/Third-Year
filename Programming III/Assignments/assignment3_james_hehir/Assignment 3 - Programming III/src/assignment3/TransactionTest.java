//James Hehir 13318731

package assignment3;


import java.io.*;



public class TransactionTest {

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		String directory = System.getProperty("user.dir"); 
		
		
		Transaction[] transactions = new Transaction[3];
		
		transactions[0] = new Transaction("16/08/2017","Open Account",100);
		transactions[1] = new Transaction("22/08/2017","Withdraw",100);
		transactions[2] = new Transaction("23/09/2017","Deposit",100);
		
		
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("transactions.txt"));
		outputStream.writeObject(transactions);
		
		
	
		@SuppressWarnings("resource")
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("transactions.txt")); //reading array
		Transaction[] transactionsCopy = (Transaction[]) inputStream.readObject();
		
		for (Transaction element : transactionsCopy) {
			System.out.println(element.toString());
		}
		
		
		BankAccount bankAcc = new BankAccount("Jenny Lee","16/08/2017",100);
		System.out.println(bankAcc.toString()+"\n");
		
		System.out.println("Withdraw 200");
		bankAcc.withdraw("22/08/2017", 200);
		System.out.println("Deposit 100");
		bankAcc.deposit("23/08/2017", 100);
		System.out.println("Withdraw 50");
		bankAcc.withdraw("01/09/2017", 50);
		
		
		System.out.println(bankAcc.getTransactionDetail());
		outputStream.writeObject(bankAcc);
		
		
		ObjectOutputStream outputStream1 = new ObjectOutputStream(new FileOutputStream("accountDetails.txt"));
		
		
		
		ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream("accountDetails.txt"));
		BankAccount bankAccCopy = (BankAccount) inputStream.readObject();
		
		System.out.println(bankAccCopy.toString()+"\n");
		System.out.println(bankAccCopy.getTransactionDetail());
		
        RandomAccessFile file = new RandomAccessFile(directory +"/Part3.txt", "rw"); //creates a random text file
       
        file.seek(file.length());
        file.writeChars("Yes");
        
        
        file.seek(0);
        String line;
        while((line = file.readLine()) != null)
			System.out.println(line);
        
        outputStream1.close();
		outputStream.close();
		inputStream1.close();
        file.close();
       
	}
	
}