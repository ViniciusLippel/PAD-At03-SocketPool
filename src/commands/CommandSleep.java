package commands;

public class CommandSleep implements ICommand{

	private String[] command;
	
	public CommandSleep(String[] command) {
		this.command = command;
	}
	
	@Override
	public boolean validate() {
		if(command.length == 2) {
			if(isNumeric(command[1])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
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
