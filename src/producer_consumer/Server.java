package producer_consumer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	public static void main(String[] args) throws IOException{
		ServerSocket servidor = null;
		Socket conexao = null;
				
		try {
			servidor = new ServerSocket(6999);
			
			
			while(true) {
				conexao = servidor.accept();
				
				ClientHandler clientHandler = new ClientHandler(conexao);
				clientHandler.start();
				
			}
				
		}catch (IOException e) {
			System.out.println("Algo de errado aconteceu");
		}finally {
			servidor.close();
		}
	}
}
