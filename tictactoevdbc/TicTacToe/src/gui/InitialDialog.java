package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitialDialog {

	private JFrame frame;

	private JTextField nTxt;
	private JTextField mTxt;
	private JTextField lineTxt;
	private JButton button;

	public InitialDialog() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button = new JButton("Start!");

		addEventForButton();

		nTxt = new JTextField(10);
		mTxt = new JTextField(10);
		lineTxt = new JTextField(10);
		nTxt.setText("5");
		mTxt.setText("5");
		lineTxt.setText("4");
	}

	public void show() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Tic Tac Toe!");
		JLabel n = new JLabel("Nr linii (minim 5): ");
		JLabel m = new JLabel("Nr coloane (minim 5): ");
		JLabel line = new JLabel("Nr de casute pentru linie(4 sau 5): ");
		addComponents(panel, title, n, m, line);
	}

	private void addComponents(JPanel panel, JLabel title, JLabel n, JLabel m,
			JLabel line) {
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.NORTH, title);

		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel);
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.EAST;
		GridBagConstraints right = new GridBagConstraints();
		right.weightx = 2.0;
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(n, left);
		panel.add(nTxt, right);
		panel.add(m, left);
		panel.add(mTxt, right);
		panel.add(line, left);
		panel.add(lineTxt, right);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		frame.pack();
		frame.setVisible(true);
	}

	private void addEventForButton() {
		button.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String nValue = nTxt.getText();
				String mValue = mTxt.getText();
				String lineValue = lineTxt.getText();

				int n = 0, m = 0, line = 0;
				try {
					n = Integer.parseInt(nValue);
					m = Integer.parseInt(mValue);
					line = Integer.parseInt(lineValue);
				} catch (NumberFormatException exception) {
					System.out.println("Exceptie la conversie");
				}
				if (n < 5 || m < 5 || (line != 4 && line != 5)) {
					JOptionPane
							.showMessageDialog(
									null,
									"Nu ai introdus date corecte.\nJocul va porni cu setarile implicite.\n5 linii\n5coloane\n4 pozitii pentru punct.");
					n = 5;
					m = 5;
					line = 4;
				} else {
					JOptionPane.showMessageDialog(null, "Porneste jocul");
				}
				frame.setVisible(false);
				Draw dp = new Draw(n, m, line);
				dp.show();
			}
		});
	}
}
