package producer_consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class ClientHandler extends Thread{
	
	private Socket socket;
	
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
			String texto = null;
			
			try {
				texto = entrada.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(texto);
			
			if(texto == null) {
				break;
			}
			
			String[] msg = texto.split(" ");
			
			if(msg[0].equals("fib")) {
				new Thread(new FibThread(rand.nextInt(20))).start();
			}
			
			else if(msg[0].equals("getFib")) {
				new Thread(new ConsumerThread(new FibThread(0))).start();
			}
			
		}while (!"sair".equals(entrada.toString()));		
	}
}
