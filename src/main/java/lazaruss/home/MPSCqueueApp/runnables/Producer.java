package lazaruss.home.MPSCqueueApp.runnables;

import java.util.concurrent.ThreadLocalRandom;
import lazaruss.home.MPSCqueueApp.queue.AbstractNodeQueue;

public class Producer implements Runnable {
	
	private AbstractNodeQueue<Integer> queue;
	int generatedNumbers;

	public Producer(AbstractNodeQueue<Integer> queue) {
		super();
		this.queue = queue;
		generatedNumbers = 0;
	}

	public void run() {
		try{
			generateNumbers();
		} catch (InterruptedException e){
			System.out.println("\n\tproducer "+(Thread.currentThread().getId() % 4 +1)+" is finished.\n");
			queue.add(-1 * generatedNumbers);
			Thread.currentThread().interrupt();
		}
	}
	
	private void generateNumbers() throws InterruptedException{
		while(true){
			int result = ThreadLocalRandom.current().nextInt(100);
			if(result == 75){
				throw new InterruptedException();
			}
			queue.add(result);
			generatedNumbers++;
			System.out.println("\tproducer "+(Thread.currentThread().getId() % 4 + 1)+" produced value "+result);
		}
	}

}
