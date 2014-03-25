package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

class DrawPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelContent mc;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawLine(10, 10, 100, 100);

	}

	@Override
	public void update(Observable modelContent, Object description) {

		mc = (ModelContent) modelContent;
		if (!mc.isPrimu()) {
			// redesenarea
		}
		if (!mc.isJocnou()) {

			// redesenarea
		}
	}
}
