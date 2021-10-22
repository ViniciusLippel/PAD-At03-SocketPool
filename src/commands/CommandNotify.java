package commands;

public class CommandNotify implements ICommand{
	
	private String[] command;
	
	public CommandNotify(String[] command) {
		this.command = command;
	}
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
