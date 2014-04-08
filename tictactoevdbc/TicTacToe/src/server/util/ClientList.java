package server.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientList {
	private Map<String, Socket> clients;
	
	private static ClientList instance =  null;
	
	private ClientList(){
		clients = new HashMap<>();
	}
	
	public static ClientList getInstance(){
		if(instance == null)
			instance = new ClientList();
		
		return instance;
	}
	
	public void addClient(String name,Socket socket){
		clients.put(name, socket);
	}
	
	public int size(){
		return clients.size();
	}
}
