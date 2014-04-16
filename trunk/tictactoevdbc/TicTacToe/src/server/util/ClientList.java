package server.util;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClientList {
	private Map<String, ObjectOutputStream> clients;
	
	private static ClientList instance =  null;
	
	private ClientList(){
		clients = new HashMap<>();
	}
	
	public synchronized static ClientList getInstance(){
		if(instance == null)
			instance = new ClientList();
		
		return instance;
	}
	
	public void addClient(String name,ObjectOutputStream oos){
		clients.put(name, oos);
	}
	
	public int size(){
		return clients.size();
	}
	
	public synchronized List<String> getClientList(){
		Set<String> clientsSet;
		
		clientsSet = clients.keySet();
		
		List<String> list = new ArrayList<>();
		
		for(String s:clientsSet)
			list.add(s);
			
		return list;
	}
	
	public ObjectOutputStream getClientSocket(String username){
		return clients.get(username);
	}
	
	public void removeClient(String username){
		clients.remove(username);
	}
	
	public List<ObjectOutputStream> getClientsStream(){
		List<ObjectOutputStream> ooss = new ArrayList<>();
		Set<String> clientsName = clients.keySet();
		
		for(String s:clientsName)
			ooss.add(clients.get(s));
		
		return ooss;
	}
}
