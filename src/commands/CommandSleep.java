package commands;

public class CommandSleep implements ICommand{

	private String[] command;
	
	public CommandSleep(String[] command) {
		this.command = command;
	}
	
	@Override
	public boolean validate() {
		if(command.length == 3) {
			//if(command[1])
		}
		return false;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
