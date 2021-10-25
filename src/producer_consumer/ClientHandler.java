package producer_consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import commands.*;

public class ClientHandler extends Thread{
	
	private Socket socket;
	public final static Object lock = new Object();
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	public static Object getLock() {
		return lock;
	}

	@Override
	public void run() {
		BufferedReader entrada = null;
		System.out.println("New Client");
		
		try {
			entrada = new BufferedReader (new InputStreamReader (this.socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		do {
			String text = null;
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
				validateExecute(command);
				break;
			case "sleep":
				command = new CommandSleep(msg);
				if(validate(command)) {
					try {
						commandSleep(Integer.parseInt(msg[1]));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "wait":
				command = new CommandWait(msg);
				if(validate(command))
					commandWait();
				break;	
			case "notify":
				command = new CommandWait(msg);
				if(validate(command))
					commandNotify();
				break;
			}
			
			
		}while (!"sair".equals(entrada.toString()));
	}
	
	public void validateExecute(ICommand command) {
		if(command.validate()) {
			command.execute();
		}
		else
			System.out.println("Invalid command");
	}
	
	public boolean validate(ICommand command) {
		if(command.validate())
			return true;
		System.out.println("Invalid Command");
		return false;
	}
	
	public void commandWait() {
		synchronized(lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void commandNotify() {
		synchronized(lock) {
			lock.notifyAll();
		}
	}
	
	public void commandSleep(int mills) throws InterruptedException{
		synchronized(lock) {
			sleep(mills);
		}
	}
}
