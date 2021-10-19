package producer_consumer;

import java.util.ArrayList;

public class ConsumerThread implements Runnable{

	private ArrayList<Integer> list = new ArrayList<Integer>();
	private FibThread fibThread;
	
	public ConsumerThread(FibThread fibThread) {
		this.fibThread = fibThread;
	}
	
	public void setFibThread(FibThread fibThread) {
		this.fibThread = fibThread;
	}
	
	public void run() {
		synchronized(fibThread) {
			boolean b = true;
			
			while(b) {
				if(this.fibThread.getBuffer().size() > 0) {
					this.list.add(fibThread.remove());
					b = false;
				}
				else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	
}
