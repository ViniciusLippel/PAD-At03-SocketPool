package commands;

public interface ICommand {
	
	public boolean validate();
	
	public void execute();
}
