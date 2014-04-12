package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.ClientComunication;

public class LoginForm {
	private JFrame frame;
	private JPanel panel;
	private JTextField username;
	private JLabel userLabel;
	private JButton button;
	
	public LoginForm(final ClientComunication clientComunication){
		frame = new JFrame();
		panel = new JPanel();
		username = new JTextField();
		username.setColumns(30);
		userLabel = new JLabel("Username:");
		button = new JButton("Login");

		panel.add(userLabel);
		panel.add(username);
		panel.add(button);
		
		frame.setSize(400, 300);
		frame.setLocation(300, 200);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(username.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "You must type an username!", "Erorr", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				frame.dispose();
		
				clientComunication.connectToServer(username.getText());
				
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void showForm(){
		frame.show();
	}
	
	public void canNotConnectMessage(){
		JOptionPane.showMessageDialog(frame, "Can not connect to server. Please try again.", "Erorr", JOptionPane.ERROR_MESSAGE);
	}
	
}
