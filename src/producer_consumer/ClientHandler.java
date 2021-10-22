package producer_consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import commands.*;

public class ClientHandler extends Thread{
	
	private Socket socket;
	private final static Object lock = new Object();
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		BufferedReader entrada = null;
		
		try {
			entrada = new BufferedReader (new InputStreamReader (this.socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		do {
			String text = null;
			System.out.println("do/while loop");
			try {
				text = entrada.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(text);
			
			if(text == null) {
				break;
			}
			
			String[] msg = text.split(" ");
			
			ICommand command = null;
			
			switch(msg[0]) {
			case "new":
				command = new CommandNew(msg);
				execute(command);
				break;
			case "sleep":
				command = new CommandSleep(msg);
				execute(command);
				break;
			case "wait":
				synchronized(lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;	
			case "notify":
				//command = new CommandNotify(msg);
				System.out.println("notifying");
				synchronized(lock) {
					lock.notifyAll();
				}
				break;
			}
			
			
		}while (!"sair".equals(entrada.toString()));
	}
	
	public void execute(ICommand command) {
		if(command.validate()) {
			command.execute();
		}
		else
			System.out.println("Invalid command");
	}
	
	public void clientWait() throws InterruptedException {
		lock.wait();
	}
	
	public void clientNotify() {
		lock.notifyAll();
	}
}
