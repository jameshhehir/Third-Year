package assignment7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;

public class Server implements Runnable{
    private final ArrayBlockingQueue<String> toServe;
    private int chefsActive;
    private final String name;
    
    private final HashMap<String, Integer> ordersServed;
    
    public Server(String name, int numChefs)
    {
        this.name = name;
        toServe = new ArrayBlockingQueue<String>(1000);
        ordersServed = new HashMap<String, Integer>();
        chefsActive = numChefs;
        new Thread(this).start();
    }
    @Override
    public void run() {
        while(true)
        {
            synchronized(toServe)
            {
                if(toServe.isEmpty()) {
					if(chefsActive <= 0)
					{
					    printOutWork();
					    break;
					} else {
					}
				} else {
                    String serve;
                    try {
                        serve = toServe.take();
                        String key = serve.split("\\d+", 2)[0];
                        if(ordersServed.get(key) == null)
							ordersServed.put(key, 1);
						else {
                            ordersServed.put(key, ordersServed.get(key) + 1);
                        }
                        printOutOrderServed(serve);
                        Thread.sleep(500);
                        toServe.notify();//notify its done with the resource
                    } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
					}
                }
            }
        }
    }
    public synchronized void addOrder(String order)
    {
        toServe.offer(order);
    }
    public void addActiveChef()
    {
        chefsActive++;
    }
    public void reduceActiveChefs()
    {
        chefsActive--;
    }
    private void printOutOrderServed(String order)
    {
        String out = "Server " + name + " is serving " + order;
        System.out.println(out);
    }
    public void printOutWork()
    {
        int totalNum = 0;
        for(Integer value : ordersServed.values())
			totalNum += value;
        String out = "Server " + name + " finished preparing " + totalNum
                + " orders, including ";
        for (Iterator<Entry<String, Integer>> iterator = ordersServed.entrySet().iterator(); iterator.hasNext();) {
			HashMap.Entry<String, Integer> entry = iterator.next();
			out += " " + entry.getKey() + " " + entry.getValue();
		}
        System.out.println(out);
    }
}