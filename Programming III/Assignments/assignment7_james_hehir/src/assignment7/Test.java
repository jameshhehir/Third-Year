package assignment7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
    private static int numChefs = 2;
    private static String fileLocation = "src/orderList.txt";
    @SuppressWarnings("unused")
	public static void main(String... args) throws FileNotFoundException
    {
        BufferedReader file;
        ArrayBlockingQueue<String> orders = new ArrayBlockingQueue<String>(1000);
        try {
            FileReader in = new FileReader(fileLocation);
            file = new BufferedReader(in);
            String line;
            while((line = file.readLine()) != null)
				orders.offer(line);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
		}
        ArrayBlockingQueue<Server> servers = new ArrayBlockingQueue<Server>(10);
        Server katie = new Server("Katie", numChefs);
        Server andrew = new Server("Andrew", numChefs);
        Server emily = new Server("Emily", numChefs);
        servers.offer(katie);
        servers.offer(andrew);
        servers.offer(emily);
        
        Chef mark = new Chef("Mark", orders, servers);
        Chef john = new Chef("John", orders, servers);
    }
}


