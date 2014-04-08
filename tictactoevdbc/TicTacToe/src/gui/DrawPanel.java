package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ModelContent;
import controller.Controller;
import controller.Mouse;

public class DrawPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Controller c;

	private int n;
	private int m;
	private int line;

	private JLabel bottom;

	private int width;
	private int height;

	private Mouse mouseListener;

	public DrawPanel() {
		super(new BorderLayout());

		c = new Controller();

		c.getM().initMatrix(5, 5, 4);

		setFields();
		setBottom();

		c.getM().setNrpozpunct(line);

		width = getWidth();
		height = getHeight();

		mouseListener = new Mouse(c, width, height);
		addMouseListener(mouseListener);
	}

	public DrawPanel(int n, int m, int line) {
		super(new BorderLayout());

		c = new Controller();

		c.getM().initMatrix(n, m, line);

		setFields();
		setBottom();

		c.getM().setNrpozpunct(line);

		width = getWidth();
		height = getHeight();

		mouseListener = new Mouse(c, width, height);
		addMouseListener(mouseListener);
	}

	private void setBottom() {
		c.getM().getMc().addObserver(this);
		String player = new String("Urmeaza jucatorul 1");
		String scoreP1 = new String("Scor jucator 1: 0");
		String scoreP2 = new String("Scor jucator 2: 0");
		bottom = new JLabel(player + "|" + scoreP1 + "|" + scoreP2);
		add(bottom, BorderLayout.PAGE_END);
	}

	private void setFields() {
		n = c.getM().getLinii();
		m = c.getM().getColoane();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		mouseListener.setC(c);

		updateBottom();

		width = getWidth();
		height = getHeight();
		mouseListener.setWidth(width);
		mouseListener.setHeight(height);

		drawLines(g);

		drawXandO(g);

		drawLinesTrough(g);
	}

	private void drawLines(Graphics g) {
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

	private void drawXandO(Graphics g) {
		int distx = (width - 2) / m;
		int disty = (height - 50) / n;
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (c.getM().getIJElement(i, j) == 2
						|| c.getM().getIJElement(i, j) == -2) {
					int x = j * distx + distx / 4;
					int xfinal = j * distx + distx / 4 * 3;
					int y = i * disty + disty / 4;
					int yfinal = i * disty + disty / 4 * 3;
					int xr = (xfinal + x) / 2;
					int yr = (yfinal + y) / 2;
					g2.draw(new Ellipse2D.Double(xr, yr, (xfinal - x) / 2,
							(yfinal - y) / 2));
				}
				if (c.getM().getIJElement(i, j) == 1
						|| c.getM().getIJElement(i, j) == -1) {
					int x = j * distx;
					int xfinal = j * distx + distx / 4 * 3;
					int y = i * disty + disty / 4;
					int yfinal = i * disty + disty / 4 * 3;
					g.drawLine(x, y, xfinal, yfinal);
					g.drawLine(xfinal, y, x, yfinal);
				}
			}
	}

	private void drawLinesTrough(Graphics g) {
		int distx = (width - 2) / m;
		int disty = (height - 50) / n;
		int size = c.getM().getMc().getPointsList().size();
		for (int i = 0; i < size; i++) {
			int tipPct = c.getM().getMc().getPointsList().get(i).getTippunct();
			int lin = c.getM().getMc().getPointsList().get(i).getLin();
			int col = c.getM().getMc().getPointsList().get(i).getCol();
			int nrPozPct = c.getM().getMc().getNrpozpunct();
			int y = lin * disty + disty / 2;
			int x = col * distx + distx / 2;
			if (tipPct == 0) {
				g.drawLine(x, y, x + (nrPozPct - 1) * distx, y);
			}
			if (tipPct == 1) {
				g.drawLine(x, y, x, y + (nrPozPct - 1) * disty);
			}
			if (tipPct == 2) {
				g.drawLine(x, y, x + (nrPozPct - 1) * distx, y + (nrPozPct - 1)
						* disty);
			}
			if (tipPct == 3) {
				g.drawLine(x, y, x - (nrPozPct - 1) * distx, y + (nrPozPct - 1)
						* disty);
			}
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
		} else {
			player = new String("Urmeaza jucatorul 1");
		}
		bottom.setText(player + "|" + scoreP1 + "|" + scoreP2);
	}

	@Override
	public void update(Observable modelContent, Object description) {
		c.getM().setMc((ModelContent) modelContent);
		repaint();
	}
}
