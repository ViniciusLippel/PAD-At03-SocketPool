package producer_consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
	
	
	public static void main(String[] args) throws IOException{
		ServerSocket servidor = null;
		Socket conexao = null;
		
		BufferedReader entrada = null;
		Random rand = new Random();
		
		boolean close = false;
			
		try {
			servidor = new ServerSocket(7000);
			
			while(true) {
				conexao = servidor.accept();
				ClientHandler clienteHandler = new ClientHandler(conexao);
				clienteHandler.start();
			}
				
		}catch (IOException e) {
			System.out.println("Algo de errado aconteceu");
		}finally {
			servidor.close();
		}
	}
}
