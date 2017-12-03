package lazaruss.home.MPSCqueueApp;

import lazaruss.home.MPSCqueueApp.queue.AbstractNodeQueue;
import lazaruss.home.MPSCqueueApp.runnables.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int np = Runtime.getRuntime().availableProcessors();
        
        AbstractNodeQueue<Integer> aQueue = new AbstractNodeQueue<Integer>();
        
        //BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        System.out.println("\n\tstarting threads\n\tNumber of producers is "+np+"\n");
        
        // start single consumer :
        new Thread(new Consumer(aQueue, np)).start();
        
        // start multiple producers :
        for(int i=0 ; i<np ; i++){
        	new Thread(new Producer(aQueue)).start();
        }
    }
}
