package commands;

import java.util.Arrays;

public class CommandNew implements ICommand {
	
	private String[] acoes = {"fib"};
	
	@Override
	public boolean validarComando(String acao, int numVezes) {
		if(Arrays.asList(this.acoes).contains(acao) && numVezes>0)
			return true;
		return false;
	}

}
