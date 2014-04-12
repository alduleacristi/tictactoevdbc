package client;

import gui.LoginForm;


public class Client {
	public static void main(String[] args) {
		ClientComunication clientComunication = new ClientComunication();
		LoginForm loginForm = new LoginForm(clientComunication);
		loginForm.showForm();

	}
}
