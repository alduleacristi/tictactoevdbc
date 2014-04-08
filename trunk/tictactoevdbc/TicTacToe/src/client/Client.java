package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.ERequestType;
import util.Request;
import util.Response;
import util.SendName;

public class Client {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 5555;
		
		try (Socket clientSocket = new Socket(host, port);
				InputStream in = clientSocket.getInputStream();
				OutputStream out = clientSocket.getOutputStream();) {
			
			SendName sendName = new SendName();
			sendName.setName("Gigi");
			Request request = new Request();
			request.setRequestType(ERequestType.THIS_IS_MY_NAME);
			request.setSendName(sendName);
			
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			oos.writeObject(request);
			oos.flush();
			
			while(true){
				Response response = (Response) ois.readObject();
				System.out.println(response.getResponseType().getName());
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
