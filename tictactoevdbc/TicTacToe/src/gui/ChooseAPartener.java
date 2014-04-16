package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.ClientComunication;
import client.WaitForResponse;

public class ChooseAPartener {
	private JFrame frame;
	private JPanel panel,dimmensionPanel;
	private JComboBox<String> clientsCombo;
	private JComboBox<Integer> rows,columns;
	private JButton refreshButton, play;
	private ClientComunication clientComunication;
	private WaitForResponse waitForRequest;
	private String name;
	private JLabel rowsLabel,columnsLabel;

	public ChooseAPartener(List<String> clients,
			ClientComunication clientComunication, ObjectInputStream ois,
			String name) {
		this.name = name;
		frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocation(100, 100);

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 310;
		
		dimmensionPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.gridy = 320;
		GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.gridy = 350;

		clientsCombo = new JComboBox<>();

		for (String s : clients)
			clientsCombo.addItem(s);

		refreshButton = new JButton("Refresh");
		this.addrefreshButton();

		play = new JButton("Play");
		this.addPlayButton();
		if (clients.size() <= 0)
			play.setEnabled(false);
		else
			play.setEnabled(true);
		
		rows = new JComboBox<>();
		columns = new JComboBox<>();
		
		for(int i=3;i<=50;i++){
			rows.addItem(i);
			columns.addItem(i);
		}

		panel.add(clientsCombo);
		panel.add(refreshButton);
		panel.add(play);
		
		rowsLabel = new JLabel("Choose the numbers of rows: ");
		columnsLabel = new JLabel("Choose the number of columns: ");
		
		dimmensionPanel.add(rowsLabel,constraints2);
		dimmensionPanel.add(rows,constraints2);
		dimmensionPanel.add(columnsLabel,constraints3);
		dimmensionPanel.add(columns,constraints3);

		panel.add(dimmensionPanel,constraints);
		frame.add(panel);
		this.clientComunication = clientComunication;

		waitForRequest = new WaitForResponse(ois, this, name, clientComunication);
		waitForRequest.start();
	}

	private void addPlayButton() {
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				play();
			}
		});
	}

	private void play() {
		play.setEnabled(false);
		clientComunication.contactUserToPlay((String) clientsCombo
				.getSelectedItem(),name,(Integer) rows.getSelectedItem(),(Integer) columns.getSelectedItem());
	}

	public void refreshComboClients(List<String> clients) {
		clientsCombo.removeAllItems();
		for (String s : clients)
			if (!s.equals(name))
				clientsCombo.addItem(s);

		if (clients.size() <= 0)
			play.setEnabled(false);
		else
			play.setEnabled(true);
	}

	private void sendRefreshComboClientsRequest() {
		this.clientComunication.refreshClientList();
	}

	private void addrefreshButton() {
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendRefreshComboClientsRequest();
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void showForm() {
		frame.show();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
