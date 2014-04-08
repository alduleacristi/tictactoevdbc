package controller;

import java.util.ArrayList;
import java.util.List;

import model.ModelContent;
import model.Point;

//clasa Model
public class MatrixCheck {
	private ModelContent mc;

	public MatrixCheck() {
		mc = new ModelContent();
	}

	public MatrixCheck(ModelContent mc) {
		this.mc = mc;
	}

	public ModelContent getMc() {
		return mc;
	}

	public void setMc(ModelContent mc) {
		this.mc = mc;
	}

	public int getLinii() {
		return mc.getLinii();
	}

	public int getColoane() {
		return mc.getColoane();
	}

	public void setNrpozpunct(int nrpozpunct) {
		mc.setNrpozpunct(nrpozpunct);
	}

	public int getIJElement(int i, int j) {
		return mc.getIJElement(i, j);
	}

	public void setIJElement(int i, int j, int val) {
		mc.setIJElement(i, j, val);
	}

	public void modify(int i, int j) {
		mc.setPrimu(false);

		if (mc.getJucator() == 0) {
			if (mc.getIJElement(i, j) != 0) {
				return;
			}
			mc.setIJElement(i, j, 1);
			mc.setJucator(1);
			//verifLine(i, j);
			verifCol(i, j);
			verifDiag(i, j);
			verifDiag2(i, j);
		} else {
			if (mc.getIJElement(i, j) != 0) {
				return;
			}
			mc.setIJElement(i, j, 2);
			mc.setJucator(0);
			//verifLine(i, j);
			verifCol(i, j);
			verifDiag(i, j);
			verifDiag2(i, j);
		}
		verifOver();
		mc.notifyObs();
	}

	public void initMatrix(int n, int m, int nrpozpunct) {
		mc = new ModelContent();
		mc.setScorjucator1(0);
		mc.setScorjucator2(0);
		mc.setJucator(0);
		mc.setNrpozpunct(nrpozpunct);

		for (int i = 0; i < n; i++) {
			List<Integer> aux = new ArrayList<Integer>();
			mc.addPeLinii(aux);
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mc.addPeColoane(i, 0);

		if (mc.isPrimu() == true)
			mc.setJocnou(false);
		else
			mc.setJocnou(true);

		mc.setPrimu(true);
	}

	public void verifLine(int x, int y) {
		int element = mc.getIJElement(y, x);
		int nr = mc.getNrpozpunct();
		int m = mc.getColoane();
		int copyx = x;

		while (x > 0 && nr > 0) {
			x--;
			nr--;
		}

		nr = mc.getNrpozpunct() - nr + mc.getNrpozpunct();

		for (; x <= copyx; x++) {
			int i = x;
			if (i + mc.getNrpozpunct() <= m) {
				boolean ok = true;
				while (i < x + mc.getNrpozpunct() && i < m) {
					if (mc.getIJElement(y, i) != element)
						ok = false;
					i++;
				}
				if (ok) {
					Point aux = new Point();
					aux.setLin(y);
					aux.setCol(x);
					aux.setTippunct(0);
					mc.getPointsList().add(aux);
					if (element == 1) {
						mc.setScorjucator1(mc.getScorjucator1() + 1);
						for (int j = x; j < x + mc.getNrpozpunct(); j++)
							mc.setIJElement(y, j, -1);
					} else {
						mc.setScorjucator2(mc.getScorjucator2() + 1);
						for (int j = x; j < x + mc.getNrpozpunct(); j++)
							mc.setIJElement(y, j, -2);
					}
					break;
				}
			}
		}

	}

	public void verifCol(int x, int y) {
		int element = mc.getIJElement(x, y);
		int nr = mc.getNrpozpunct();
		int m = mc.getLinii();
		int copyx = x;

		while (x > 0 && nr > 0) {
			x--;
			nr--;
		}

		nr = mc.getNrpozpunct() - nr + mc.getNrpozpunct();

		for (; x <= copyx; x++) {
			int i = x;
			if (i + mc.getNrpozpunct() <= m) {
				boolean ok = true;
				while (i < x + mc.getNrpozpunct() && i < m) {
					if (mc.getIJElement(i, y) != element)
						ok = false;
					i++;
				}
				if (ok) {
					Point aux = new Point();
					aux.setLin(x);
					aux.setCol(y);
					aux.setTippunct(1);
					mc.getPointsList().add(aux);

					if (element == 1) {
						mc.setScorjucator1(mc.getScorjucator1() + 1);
						for (int j = x; j < x + mc.getNrpozpunct(); j++)
							mc.setIJElement(j, y, -1);
					} else {
						mc.setScorjucator2(mc.getScorjucator2() + 1);
						for (int j = x; j < x + mc.getNrpozpunct(); j++)
							mc.setIJElement(j, y, -2);
					}
					break;
				}
			}
		}
	}

	public void verifDiag(int x, int y) {
		int element = mc.getIJElement(x, y);
		int nr = mc.getNrpozpunct();
		int m = mc.getLinii();
		int n = mc.getColoane();
		int copyx = x;
		int copyy = y;

		while (x > 0 && y > 0 && nr > 0) {
			x--;
			y--;
			nr--;
		}

		nr = mc.getNrpozpunct() - nr + mc.getNrpozpunct();

		while (x <= copyx && y <= copyy) {
			int i = x;
			int j = y;
			if (i + mc.getNrpozpunct() <= m && j + mc.getNrpozpunct() <= n) {
				boolean ok = true;
				while (i < x + mc.getNrpozpunct() && j < y + mc.getNrpozpunct()
						&& i < m && j < n) {
					if (mc.getIJElement(i, j) != element)
						ok = false;
					i++;
					j++;
				}
				if (ok) {
					Point aux = new Point();
					aux.setLin(x);
					aux.setCol(y);
					aux.setTippunct(2);
					mc.getPointsList().add(aux);
					if (element == 1) {
						mc.setScorjucator1(mc.getScorjucator1() + 1);
						i = x;
						j = y;
						while (i < x + mc.getNrpozpunct()
								&& j < y + mc.getNrpozpunct()) {
							mc.setIJElement(i, j, -1);
							i++;
							j++;
						}
					} else {
						mc.setScorjucator2(mc.getScorjucator2() + 1);
						i = x;
						j = y;
						while (i < x + mc.getNrpozpunct()
								&& j < y + mc.getNrpozpunct()) {
							mc.setIJElement(i, j, -2);
							i++;
							j++;
						}
					}
					break;
				}
			}
			x++;
			y++;
		}
	}

	public void verifOver() {
		int nr = 0;
		for (int i = 0; i < mc.getLinii(); i++)
			for (int j = 0; j < mc.getColoane(); j++)
				if (mc.getIJElement(i, j) != 0)
					mc.setGata(false);
				else
					nr++;
		if (nr == mc.getLinii() * mc.getColoane())
			mc.setGata(true);
	}

	public void verifDiag2(int x, int y) {
		int element = mc.getIJElement(x, y);
		int nr = mc.getNrpozpunct();
		int m = mc.getLinii();
		int n = mc.getColoane();
		int copyx = x;
		int copyy = y;

		while (x > 0 && y < n - 1 && nr > 0) {
			x--;
			y++;
			nr--;
		}

		nr = mc.getNrpozpunct() - nr + mc.getNrpozpunct();

		while (x <= copyx && y >= copyy) {
			int i = x;
			int j = y;
			if (i + mc.getNrpozpunct() <= m && j + 1 - mc.getNrpozpunct() >= 0) {
				boolean ok = true;
				while (i < x + mc.getNrpozpunct() && j > y - mc.getNrpozpunct()
						&& i < m && j >= 0) {
					if (mc.getIJElement(i, j) != element)
						ok = false;
					i++;
					j--;
				}
				if (ok) {
					Point aux = new Point();
					aux.setLin(x);
					aux.setCol(y);
					aux.setTippunct(3);
					mc.getPointsList().add(aux);

					if (element == 1) {
						mc.setScorjucator1(mc.getScorjucator1() + 1);
						i = x;
						j = y;
						while (i < x + mc.getNrpozpunct()
								&& j + 1 > y - mc.getNrpozpunct() && i < m
								&& j >= 0) {
							mc.setIJElement(i, j, -1);
							i++;
							j--;
						}
					} else {
						mc.setScorjucator2(mc.getScorjucator2() + 1);
						i = x;
						j = y;
						while (i < x + mc.getNrpozpunct()
								&& j + 1 > y - mc.getNrpozpunct() && i < m
								&& j >= 0) {
							mc.setIJElement(i, j, -2);
							i++;
							j--;
						}
					}
					break;
				}
			}
			x++;
			y--;
		}
	}
}
