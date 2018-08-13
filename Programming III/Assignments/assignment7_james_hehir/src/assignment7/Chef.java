package assignment7;


import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Chef implements Runnable{
    private String name;
    private final ArrayBlockingQueue<String> currentOrders;
    private ArrayBlockingQueue<Server> servers;
    private HashMap<String, Integer> ordersPrepared;
    public Chef(String name, ArrayBlockingQueue<String> orders, ArrayBlockingQueue<Server> servers)
    {
        this.name = name;
        currentOrders = orders;
        ordersPrepared = new HashMap<String,Integer>();
        this.servers = servers;
        new Thread(this).start();
    }    
    @Override
    public void run() {
        while(true)
		 synchronized(currentOrders)
            {
                if(currentOrders.isEmpty()) {
                    Iterator<Server> serve = servers.iterator();
                    while(serve.hasNext())
                    {
                        serve.next().reduceActiveChefs();
                    }
                    printOutWork();
                    break;
                } else {
                    String order = "";
                    try {
                        order = currentOrders.take();
                        String key = order.split("\\d+", 2)[0];
                        if(ordersPrepared.get(key) != null)
                        {
                            ordersPrepared.put(key, ordersPrepared.get(key) + 1);
                        } else
							ordersPrepared.put(key, 1);
                        printOutOrderPrepared(order);
                        Thread.sleep(500);
                        Server server = servers.take();
                        server.addOrder(order);
                        servers.offer(server);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
					}
                }
            }
    }
    private void printOutOrderPrepared(String order)
    {
        String out = "Chef " + name + " is preparing " + order;
        System.out.println(out);
    }
    public void printOutWork()
    {
        int totalNum = 0;
        for(Integer value : ordersPrepared.values())
        {
            totalNum += value;
        }
        String out = "Chef " + name + " finished preparing " + totalNum
                + " orders, including ";
        for(HashMap.Entry<String, Integer> entry : ordersPrepared.entrySet())
        {
            out += " " + entry.getKey() + " " + entry.getValue();
        }
        System.out.println(out);
    }
}