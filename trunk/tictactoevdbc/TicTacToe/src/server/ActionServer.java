package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import server.util.ClientList;
import util.ERequestType;
import util.EResponseType;
import util.Request;
import util.Response;

public class ActionServer extends Thread {
	private Socket socket, partenerSocket = null;

	public ActionServer(Socket socket) {
		this.socket = socket;
	}

	private void sendListOfClients(ObjectOutputStream oos) {
		Response response = new Response();
		response.setResponseType(EResponseType.THIS_IS_LIST_OF_CLIENTS);

		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try (OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream()) {

			ObjectInputStream ois = new ObjectInputStream(in);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			
			while (true) {
				Request request = (Request) ois.readObject();

				switch (request.getRequestType()) {
				case THIS_IS_MY_NAME:
					ClientList.getInstance().addClient(
							request.getSendName().getName(), socket);

					sendListOfClients(oos);
					
					System.out.println("s-au trimis date");
					break;

				default:
					break;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
