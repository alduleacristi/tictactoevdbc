package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import service.UserService;
import client.ClientComunication;

public class LoginForm {
	private JFrame frame;
	private JPanel panel;
	private JTextField username;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton bLogin;
	private JPasswordField password;
	private JButton bSignUp;
	private JButton bCreate;
	private JTextField newUserNameField;
	private JLabel newUserNameLabel;
	private JTextField newUserPwField;
	private JLabel newUserPwLabel;

	public LoginForm(final ClientComunication clientComunication) {
		frame = new JFrame();
		panel = new JPanel();
		username = new JTextField();
		username.setColumns(30);
		password = new JPasswordField();
		password.setColumns(30);
		userLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		bLogin = new JButton("Login");
		bSignUp = new JButton("Sign Up");
		bCreate = new JButton("Create User");

		panel.add(userLabel);
		panel.add(username);
		panel.add(passwordLabel);
		panel.add(password);
		panel.add(bLogin);
		panel.add(bSignUp);

		frame.setSize(400, 300);
		frame.setLocation(300, 200);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		newUserNameField = new JTextField();
		newUserNameField.setColumns(30);
		newUserNameLabel = new JLabel("Username:");
		newUserPwField = new JTextField();
		newUserPwField.setColumns(30);
		newUserPwLabel = new JLabel("Password:");

		bLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (username.getText().equals("")) {
					JOptionPane.showMessageDialog(frame,
							"You must type an username!", "Erorr",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				UserService us = new UserService();
				String user = "", pass = "";
				try {
					user = us.getUserByUsername(username.getText())
							.getUsername();
					pass = us.getUserByUsername(username.getText())
							.getPassword();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (user.equals("") == false && pass.equals(password.getText())) {
					frame.dispose();
					
					//InitialDialog dialog = new InitialDialog();
					//dialog.show();
					clientComunication.connectToServer(user);
				} else
					JOptionPane.showMessageDialog(frame,
							"Invalid username or password!", "Erorr",
							JOptionPane.ERROR_MESSAGE);
				return;

			}
		});

		bSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panel.add(newUserNameLabel);
				panel.add(newUserNameField);
				panel.add(newUserPwLabel);
				panel.add(newUserPwField);
				panel.remove(userLabel);
				panel.remove(username);
				panel.remove(password);
				panel.remove(passwordLabel);
				panel.remove(bLogin);
				panel.remove(bSignUp);
				panel.add(bCreate);
				frame.repaint();
				frame.revalidate();
			}
		});

		bCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserService us = new UserService();
				String user = "";
				try {
					user = us.getUserByUsername(newUserNameField.getText())
							.getUsername();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (user.equals("") == false) {
					JOptionPane.showMessageDialog(frame,
							"The username is already being used or invalid!",
							"Erorr", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (newUserPwField.getText().equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Invalid password!", "Erorr",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						User u = new User();
						u.setUsername(newUserNameField.getText());
						u.setPassword(newUserPwField.getText());
						us.insertUser(u);
						
						panel.remove(newUserNameLabel);
						panel.remove(newUserNameField);
						panel.remove(newUserPwLabel);
						panel.remove(newUserPwField);
						panel.add(userLabel);
						panel.add(username);
						panel.add(bLogin);
						panel.add(bSignUp);
						panel.add(password);
						panel.add(passwordLabel);
						panel.remove(bCreate);
						frame.repaint();
					}
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void showForm() {
		frame.show();
	}

	public void canNotConnectMessage() {
		JOptionPane.showMessageDialog(frame,
				"Can not connect to server. Please try again.", "Erorr",
				JOptionPane.ERROR_MESSAGE);
	}

}
