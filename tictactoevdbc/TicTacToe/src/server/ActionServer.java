package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import server.util.ClientList;
import util.EResponseType;
import util.Request;
import util.Response;
import util.StartGame;

public class ActionServer extends Thread {
	private Socket socket;

	public ActionServer(Socket socket) {
		this.socket = socket;
	}

	private void sendListOfClients(ObjectOutputStream oos) {
		Response response = new Response();
		response.setResponseType(EResponseType.THIS_IS_LIST_OF_CLIENTS);
		response.setClients(ClientList.getInstance().getClientList());

		try {
			List<ObjectOutputStream> streams = ClientList.getInstance()
					.getClientsStream();

			for (ObjectOutputStream stream : streams) {
				stream.writeObject(response);
				stream.flush();
			}
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
							request.getSendName().getName(), oos);

					sendListOfClients(oos);
					break;
				case GIVE_CLIENT_LIST:
					this.sendListOfClients(oos);
					break;
				case CONTACT_USER_TO_PLAY:
					Response serverRequest = new Response();
					serverRequest.setResponseType(EResponseType.PLAY_REQUEST);
					serverRequest.setContactUser(request.getContactUser());

					ObjectOutputStream partenerStream = ClientList
							.getInstance().getClientSocket(
									request.getContactUser().getPlayer2());
					partenerStream.writeObject(serverRequest);
					partenerStream.flush();
					break;
				case RESPONSE_TO_PLAY_REQUEST:
					Response response = new Response();
					StartGame startGame = new StartGame();
					if (request.getResponseToPlayRequest().isAccept()) {
						System.out.println(request.getResponseToPlayRequest()
								.getPlayer1());
						System.out.println(request.getResponseToPlayRequest()
								.getPlayer2());
						partenerStream = ClientList.getInstance()
								.getClientSocket(
										request.getResponseToPlayRequest()
												.getPlayer1());
						ClientList.getInstance()
								.removeClient(
										request.getResponseToPlayRequest()
												.getPlayer1());
						ClientList.getInstance()
								.removeClient(
										request.getResponseToPlayRequest()
												.getPlayer2());
						startGame.setStart(true);
						startGame.setM(request.getResponseToPlayRequest()
								.getM());
						startGame.setN(request.getResponseToPlayRequest()
								.getN());
						response.setResponseType(EResponseType.START_GAME);
						response.setStartGame(startGame);

						partenerStream.writeObject(response);
						partenerStream.flush();

						oos.writeObject(response);
						oos.flush();
					} else {
						startGame.setStart(false);
						response.setResponseType(EResponseType.START_GAME);
						response.setStartGame(startGame);
						oos.writeObject(response);
						oos.flush();
					}

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
