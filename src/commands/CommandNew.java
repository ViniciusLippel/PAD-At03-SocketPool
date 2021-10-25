package commands;

import java.util.Arrays;
import producer_consumer.FibThread;

public class CommandNew implements ICommand {
	
	private String[] command;
	private String[] actions = {"fib"};
	
	public CommandNew(String[] command) {
		this.command = command;
	}
	
	@Override
	public boolean validate() {
		if(command.length == 4) {
			if(Arrays.asList(this.actions).contains(command[1]) && isNumeric(command[2]) && !command[3].isBlank())
				return true;
		}
		return false;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.command[1].equals("fib")) {
			new Thread(new FibThread(this.command[3], Integer.parseInt(command[2]))).start();
		}
	}
	
	
	public boolean isNumeric(String str) {
		if(str == null)
			return false;
		
		try {
			Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	

}
