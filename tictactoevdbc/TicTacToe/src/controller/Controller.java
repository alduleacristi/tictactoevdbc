package controller;

import java.util.List;

import javax.swing.JOptionPane;

public class Controller {

	MatrixCheck m;

	public Controller(MatrixCheck m) {
		this.m = m;
	}

	public Controller() {
		m = new MatrixCheck();
	}

	public MatrixCheck getM() {
		return m;
	}

	public void setM(MatrixCheck m) {
		this.m = m;
	}

	public void click(int x, int y, int width, int height) {
		List<Integer> list = m.getMc().getA().get(0);
		int n = m.getMc().getA().size();
		int mm = list.size();

		height -= 50;
		if (y > height - 10 || x > width - 10) {
			return;
		}

		height /= n;
		width /= mm;

		int i = y / height;
		int j = x / width;

		JOptionPane.showMessageDialog(null, i+" "+j);
		m.modify(i, j);
	}

	public void newGame(int n, int m, int nrpozpunct) {
		this.m.initMatrix(n, m, nrpozpunct);
	}
}
