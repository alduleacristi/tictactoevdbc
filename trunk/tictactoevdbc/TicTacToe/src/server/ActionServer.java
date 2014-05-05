package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import model.User;
import server.util.ClientList;
//import service.GameService;
import service.UserService;
import util.EResponseType;
import util.Request;
import util.Response;
import util.StartGame;

public class ActionServer extends Thread {
	private Socket socket;

	private ObjectOutputStream part1;

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
				System.out.println("req  " + request);
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
					// prin ob response serverul trimite cererea jucatorului
					Response serverRequest = new Response();
					serverRequest.setResponseType(EResponseType.PLAY_REQUEST);
					serverRequest.setContactUser(request.getContactUser());

					ObjectOutputStream partenerStream = ClientList
							.getInstance().getClientSocket(
									request.getContactUser().getPlayer2());
					
					//setez fluxul
					part1 = partenerStream;
					
					
//					stteamn = partenerStream;
//					partnerName = request.getContactUser().getPlayer2();
//					System.out.println("partn neame  "+ partnerName);
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
						
						part1= partenerStream;
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
						startGame.setLine(request.getResponseToPlayRequest().getLine());
						response.setResponseType(EResponseType.START_GAME);
						response.setStartGame(startGame);

						partenerStream.writeObject(response);
						oos.writeObject(response);

						partenerStream.flush();

					} else {
						startGame.setStart(false);
						response.setResponseType(EResponseType.START_GAME);
						response.setStartGame(startGame);
						oos.writeObject(response);
						oos.flush();
					}
					break;

				case SEND_MOVE:
					System.out.println(request.getI() + " " + request.getJ());
					Response resp = new Response();
					resp.setI(request.getI());
					resp.setJ(request.getJ());
					resp.setResponseType(EResponseType.DRAW);
				//	oos.writeObject(resp);
//					partenerStream = ClientList.getInstance().getClientSocket(
//							partnerName);
				//	System.out.println(partnerName);
//					if(stteamn == null) System.out.println(" en nul"); else System.out.println(" nu e nul");
//					stteamn.writeObject(resp);
//					stteamn.flush();oos.flush();
					
					//if(part1== null) System.out.println("e nul"); else System.out.println("nu e nul");
					part1.writeObject(resp);
					part1.flush();
					break;
				case WIN:
					resp = new Response();
					resp.setResponseType(EResponseType.WIN);
					part1.writeObject(resp);
					part1.flush();
					break;
				default:
					break;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
