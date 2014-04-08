package server;

import server.i.IServer;

public class Server {
	public static void main(String[] args) {
		
	    IServer myMServer=new ConnectivityServer();
	    myMServer.waitForClients(5555);
	}
}
