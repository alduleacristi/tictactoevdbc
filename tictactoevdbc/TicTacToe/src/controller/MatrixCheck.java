package controller;

import gui.ModelContent;

import java.util.ArrayList;
import java.util.List;

//clasa Model
public class MatrixCheck {
	private ModelContent mc;

	public MatrixCheck() {
		mc = new ModelContent();
	}

	public ModelContent getMc() {
		return mc;
	}

	public void setMc(ModelContent mc) {
		this.mc = mc;
	}

	public void modify(int i, int j) {

	}

	public void initMatrix(int n, int m, int nrpozpunct) {
		mc = new ModelContent();
		mc.setScorjucator1(0);
		mc.setScorjucator2(0);
		mc.setJucator(0);
		mc.setNrpozpunct(nrpozpunct);

		for (int i = 0; i < n; i++) {
			List<Integer> aux = new ArrayList<Integer>();
			mc.getA().add(aux);
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mc.getA().get(i).add(0);

		if (mc.isPrimu() == true)
			mc.setJocnou(false);
		else
			mc.setJocnou(true);

		mc.setPrimu(true);
		// NotifyAll();
	}

	public void verifLine(int x, int y) {

	}

	public void verifCol(int x, int y) {

	}

	public void verifDiag(int x, int y) {

	}

	public void verifOver() {

	}

	public void verifDiag2(int x, int y) {

	}
}
