package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import util.ERequestType;
import util.Request;
import util.Response;
import util.ResponseToPlayRequest;
import gui.ChooseAPartener;
import gui.Draw;

public class WaitForResponse extends Thread {
	private ChooseAPartener clientServer;
	private ObjectInputStream ois;
	private String name;
	private ClientComunication clientComunication;
	private Draw draw;

	public WaitForResponse(ObjectInputStream ois, ChooseAPartener clientServer,
			String name, ClientComunication clientComunication) {
		this.name = name;
		this.ois = ois;
		this.clientServer = clientServer;
		this.clientComunication = clientComunication;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			while (true) {
				Response response = (Response) ois.readObject();

				switch (response.getResponseType()) {
				case THIS_IS_LIST_OF_CLIENTS:
					clientServer.refreshComboClients(response.getClients());
					break;
				case PLAY_REQUEST:
					int option = JOptionPane.showConfirmDialog(
							clientServer.getFrame(),
							"Do you want to play with: "
									+ response.getContactUser().getPlayer1()
									+ " using a table with "
									+ response.getContactUser().getN()
									+ " rows and "
									+ response.getContactUser().getM()
									+ " columns? (You will be player 2 )",
							"Play request", JOptionPane.OK_CANCEL_OPTION);

					Request request = new Request();
					request.setRequestType(ERequestType.RESPONSE_TO_PLAY_REQUEST);
					ResponseToPlayRequest responseToPlayRequest = new ResponseToPlayRequest();
					if (option == 0) {

						responseToPlayRequest.setAccept(true);
						responseToPlayRequest.setPlayer1(response
								.getContactUser().getPlayer1());
						responseToPlayRequest.setPlayer2(name);
						responseToPlayRequest.setM(response.getContactUser()
								.getM());
						responseToPlayRequest.setN(response.getContactUser()
								.getN());
						responseToPlayRequest.setLine(response.getContactUser()
								.getLine());
						draw = new Draw(response.getContactUser().getN(),
								response.getContactUser().getM(), response
										.getContactUser().getLine(),
								clientComunication);
						draw.getPanel().getC().setSend(true);
					} else
						responseToPlayRequest.setAccept(false);

					request.setResponseToPlayRequest(responseToPlayRequest);

					clientComunication.acceptResponse(request);
					break;

				case START_GAME:
					if (response.getStartGame().isStart()) {

						if (draw == null) {
							JOptionPane.showMessageDialog(null,
									"You will be player 1 ");
							draw = new Draw(response.getStartGame().getN(),
									response.getStartGame().getM(), response
											.getStartGame().getLine(),
									clientComunication);
							draw.getPanel().getC().setSend(false);
						}
						draw.show();
					}

					break;
				case DRAW:
					System.out.println(response.getI() + " " + response.getJ());
					draw.getPanel().getC().setSend(false);
					draw.getPanel().getC().getM()
							.modify(response.getI(), response.getJ());
					break;
				default:
					break;

				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
