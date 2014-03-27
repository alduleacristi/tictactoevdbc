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

	public MatrixCheck(ModelContent mc) {
		this.mc = mc;
	}

	public ModelContent getMc() {
		return mc;
	}

	public void setMc(ModelContent mc) {
		this.mc = mc;
	}

	public void modify(int i, int j) {
		System.out.println("am intrat in modify");
		mc.setPrimu(false);

		if (mc.getJucator() == 0) {
			System.out.println("jucator 1");
			if (mc.getA().get(i).get(j) != 0) {
				return;
			}
			mc.getA().get(i).set(j, 1);
			mc.setJucator(1);
			// VerifLinie(i,j);
			// VerifCol(i,j);
			// VerifDiag(i,j);
			// VerifDiag2(i,j);
		} else {
			System.out.println("jucator 2");
			if (mc.getA().get(i).get(j) != 0) {
				return;
			}
			mc.getA().get(i).set(j, 2);
			mc.setJucator(0);
			// VerifLinie(i,j);
			// VerifCol(i,j);
			// VerifDiag(i,j);
			// VerifDiag2(i,j);
		}
		// VerifGata();
		// System.out.println("acu notificam");
		// mc.notifyObservers();
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
		// mc.notifyObservers();
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
