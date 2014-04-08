package model;

public class Point {
	private int lin, col, tippunct;

	public Point(int lin, int col, int tippunct) {
		this.lin = lin;
		this.col = col;
		this.tippunct = tippunct;
	}

	public Point() {
	}

	public int getLin() {
		return lin;
	}

	public void setLin(int lin) {
		this.lin = lin;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getTippunct() {
		return tippunct;
	}

	public void setTippunct(int tippunct) {
		this.tippunct = tippunct;
	}

}
