package controller;

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
		int n = m.getLinii();
		int mm = m.getColoane();

		height -= 50;
		if (y > height - 10 || x > width - 10) {
			return;
		}

		height /= n;
		width /= mm;

		int i = y / height;
		int j = x / width;

		m.modify(i, j);
	}

	public void newGame(int n, int m, int nrpozpunct) {
		this.m.initMatrix(n, m, nrpozpunct);
	}
}
