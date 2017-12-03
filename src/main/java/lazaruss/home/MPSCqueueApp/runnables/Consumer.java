package lazaruss.home.MPSCqueueApp.runnables;

import lazaruss.home.MPSCqueueApp.queue.AbstractNodeQueue;

public class Consumer implements Runnable {
	
	private AbstractNodeQueue<Integer> queue;
	private int activeProducers;
	private int consumedNumbers;
	
	public Consumer(AbstractNodeQueue<Integer> queue, int activeProducers) {
		super();
		this.activeProducers = activeProducers;
		this.queue = queue;
		consumedNumbers = 0;
	}

	public void run() {

		int totalGeneratedNumbers = 0;
		while(true){
			
			Integer number = queue.poll();
			if(number!=null){
				if(number<0){
					activeProducers--;
					totalGeneratedNumbers -= number;
				} else{
					System.out.println("Consumer retreaving result: "+number);
					consumedNumbers ++;
				}
			}
			if(activeProducers==0 && queue.isEmpty()){
				System.out.println("\n\tTotal generated numbers : "+totalGeneratedNumbers);
				System.out.println("\tTotal consumed numbers : "+consumedNumbers+"\n");
				break;
			}
		}

		System.out.println("\n\tMy job is done. :)\n");
	}
}