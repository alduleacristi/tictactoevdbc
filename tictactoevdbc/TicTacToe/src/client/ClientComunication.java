package client;

import gui.ChooseAPartener;
import gui.LoginForm;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import util.ContactUser;
import util.ERequestType;
import util.Request;
import util.Response;
import util.SendName;

public class ClientComunication {
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private final String host = "localhost"; 
	private final int port = 5555;
	private String name;
	

	public ClientComunication() {

		try {
			clientSocket = new Socket(host, port);
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();

			oos = new ObjectOutputStream(out);
			ois = new ObjectInputStream(in);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			LoginForm login = new LoginForm(this);
			login.showForm();
			login.canNotConnectMessage();
		}

	}
	
	private void sendRequest(Request request) throws IOException{
			oos.writeObject(request);
			oos.flush();
	}

	private void showClientsList() {
		try {
			Response response = (Response) ois.readObject();
			
			List<String> clients = response.getClients();
			for(int i=0;i<clients.size();i++)
				if(clients.get(i).equals(name)){
					clients.remove(i);
					i--;
				}
			
			ChooseAPartener clientsServer = new ChooseAPartener(clients,this,ois,name);
			clientsServer.showForm();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connectToServer(String name) {
		this.name = name;
		SendName sendName = new SendName();
		sendName.setName(name);

		Request request = new Request();
		request.setRequestType(ERequestType.THIS_IS_MY_NAME);
		request.setSendName(sendName);
		
		try {
			this.sendRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showClientsList();
	}
	
	public void refreshClientList(){
		Request request = new Request();
		request.setRequestType(ERequestType.GIVE_CLIENT_LIST);
		
		try {
			this.sendRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void sendUpdatedMatrix(List<List<Integer>> list){
		
	}
	public void contactUserToPlay(String player2,String player1, Integer n,Integer m, Integer line){
		ContactUser contactUser = new ContactUser();
		contactUser.setPlayer2(player2);
		contactUser.setPlayer1(player1);
		contactUser.setM(m);
		contactUser.setN(n);
		contactUser.setLine(line);
		Request request = new Request();
		request.setRequestType(ERequestType.CONTACT_USER_TO_PLAY);
		request.setContactUser(contactUser);
		
		try {
			this.sendRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void acceptResponse(Request request){
		try {
			this.sendRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMove(int i , int j){
		Request req = new Request();
		req.setI(i);
		req.setJ(j);
		req.setRequestType(ERequestType.SEND_MOVE);
		ContactUser us = new ContactUser();
		us.setPlayer1(name);
		req.setContactUser(us);
		try {
			this.sendRequest(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void finishGame(){
		Request req = new Request();
		req.setRequestType(ERequestType.WIN);
		
		try {
			this.sendRequest(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
