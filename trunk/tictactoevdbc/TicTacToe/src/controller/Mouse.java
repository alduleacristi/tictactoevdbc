package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import exceptii.ClicException;

public class Mouse implements MouseListener {

	private Controller c;
	private int width;
	private int height;

	public Mouse(Controller c, int width, int height) {
		super();
		this.c = c;
		this.width = width;
		this.height = height;
		System.out.println(c.getM().getMc().countObservers());
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			c.click(e.getX(), e.getY(), width, height);
		} catch (ClicException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
