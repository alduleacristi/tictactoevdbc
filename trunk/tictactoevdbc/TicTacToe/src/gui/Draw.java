package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import database.DataBase;

public class Draw extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public Draw() {
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawPanel();
		panel.setBackground(Color.cyan);
		add(panel);
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Draw d = new Draw();
		d.panel.repaint();
		d.show();
	}
}
