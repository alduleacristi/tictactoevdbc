package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public Draw(int n, int m, int line) {
		int h = 1000;
		int w = 600;
		setSize(h, w);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawPanel(n, m, line);
		panel.setBackground(Color.lightGray);
		add(panel);
	}

	public static void main(String[] args) {
		InitialDialog dialog = new InitialDialog();
		dialog.show();
	}
}
