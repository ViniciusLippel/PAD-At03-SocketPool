package producer_consumer;

import java.util.LinkedList;

public class FibThread implements Runnable {
	
	private String name;
	private int fib;
	private final int capacity = 5;
	private static LinkedList<Integer> buffer = new LinkedList<Integer>();

	public FibThread(String name, int num) {
		this.name = name;
		this.fib = Fib(num);
	}

	public LinkedList<Integer> getBuffer() {
		return buffer;
	}

	public void run() {
		add(this.fib);
	}
	
	public int Fib(int num) {
		if(num <= 1)
			return num;
		return Fib(num-1) + Fib(num-2);
	}

	//Add
	public synchronized void add(int value) {

		boolean b = true;

		synchronized(buffer) {
			while (b) {
				if(buffer.size() < capacity) {
					buffer.add(value);
					System.out.println("Adding fib. " + this.name + ": " + value);
					notify();
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

	//Remove
	public synchronized int remove() {

		int value = buffer.poll();
		System.out.println("removing " + value);
		return value;
	}
}
