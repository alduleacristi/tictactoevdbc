package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.i.IServer;

public class ConnectivityServer implements IServer {
	

	private ServerSocket getServerSocket(int port) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + port);
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Server is ready ...");
		return serverSocket;
	}

	@Override
	public void waitForClients(int port) {
		ServerSocket serverSocket = getServerSocket(port);

		while (true) {
			try {
				Socket socket = serverSocket.accept();
				
				ActionServer actionServer = new ActionServer(socket);
				actionServer.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
