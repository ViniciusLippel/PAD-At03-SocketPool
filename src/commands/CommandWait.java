package commands;

public class CommandWait implements ICommand {
	
	private String[] command;
	
	public CommandWait(String[] command) {
		this.command = command;
	}
	
	@Override
	public boolean validate() {
		if(command.length == 1)
			return true;
		return false;
	}
	
	@Override
	public void execute() {
	}
}
