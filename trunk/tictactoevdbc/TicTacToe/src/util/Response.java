package util;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

	private static final long serialVersionUID = -980121447706239156L;
	private EResponseType responseType;
	private List<String> clients;
	private ContactUser contactUser;
	private StartGame startGame;

	public EResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(EResponseType responseType) {
		this.responseType = responseType;
	}

	public List<String> getClients() {
		return clients;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public ContactUser getContactUser() {
		return contactUser;
	}

	public void setContactUser(ContactUser contactUser) {
		this.contactUser = contactUser;
	}

	public StartGame getStartGame() {
		return startGame;
	}

	public void setStartGame(StartGame startGame) {
		this.startGame = startGame;
	}

	@Override
	public String toString() {
		return "Response [responseType=" + responseType + ", clients="
				+ clients + ", contactUser=" + contactUser + ", startGame="
				+ startGame + "]";
	}
	
	
}
