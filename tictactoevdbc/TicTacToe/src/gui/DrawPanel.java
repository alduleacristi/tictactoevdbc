package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.MatrixCheck;

public class DrawPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Controller c;

	private int n;
	private int m;
	private int line;

	private JLabel bottom;

	private int width;
	private int height;

	public DrawPanel() {
		super(new BorderLayout());
		c = new Controller();
		c.getM().initMatrix(5, 5, 4);
		setFields();
		setBottom();
		c.getM().getMc().setNrpozpunct(line);
		width = getWidth();
		height = getHeight();
	}

	public DrawPanel(int n, int m, int line) {
		super(new BorderLayout());
		MatrixCheck matrix = new MatrixCheck();
		c = new Controller(matrix);
		c.getM().initMatrix(n, m, line);
		setFields();
		setBottom();
		c.getM().getMc().setNrpozpunct(line);
		width = getWidth();
		height = getHeight();

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.click(e.getX(), e.getY(), width, height);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// System.out.println("mouse entered");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// System.out.println("mouse exited");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("mouse presses");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// System.out.println("mouse released");
			}
		});
	}

	private void setBottom() {
		String player = new String("Urmeaza jucatorul 1");
		String scoreP1 = new String("Scor jucator 1: 0");
		String scoreP2 = new String("Scor jucator 2: 0");
		bottom = new JLabel(player + "|" + scoreP1 + "|" + scoreP2);
		add(bottom, BorderLayout.PAGE_END);
	}

	private void setFields() {
		List<Integer> list = c.getM().getMc().getA().get(0);
		line = c.getM().getMc().getNrpozpunct();
		n = c.getM().getMc().getA().size();
		m = list.size();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		updateBottom();

		width = getWidth();
		height = getHeight();

		drawLines(g, width, height, n, m);
	}

	private void drawLines(Graphics g, int width, int height, int n, int m) {

		int w = width;
		int h = height - 50;

		int dist = (w - 2) / m;
		int x = 2;

		for (int i = 0; i <= n; i++) {
			g.drawLine(x, 2, x, height - 50);
			x += dist;
		}

		dist = (h - 2) / n;
		int y = 2;

		for (int i = 0; i <= m; i++) {
			g.drawLine(2, y, width - 2, y);
			y += dist;
		}

	}

	private void updateBottom() {
		String player;
		String scoreP1 = new String("Scor jucator 1: "
				+ c.getM().getMc().getScorjucator1());
		String scoreP2 = new String("Scor jucator 2: "
				+ c.getM().getMc().getScorjucator2());
		if (c.getM().getMc().getJucator() == 1) {
			player = new String("Urmeaza jucatorul 2");
		} else
			player = new String("Urmeaza jucatorul 1");
		bottom.setText(player + "|" + scoreP1 + "|" + scoreP2);
	}

	@Override
	public void update(Observable modelContent, Object description) {
		System.out.println("redesenare update");
		c.getM().setMc((ModelContent) modelContent);
		if (!c.getM().getMc().isPrimu()) {
			// redesenarea
		}
		if (!c.getM().getMc().isJocnou()) {
			// redesenarea
		}
	}
}
