package controller;

import javax.swing.JOptionPane;

import exceptii.ClicException;

import client.ClientComunication;

public class Controller {

	MatrixCheck m;
	ClientComunication cm;
	boolean send;

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public Controller(ClientComunication cm) {
		this.cm = cm;
		m = new MatrixCheck();
	}

	public MatrixCheck getM() {
		return m;
	}

	public void setM(MatrixCheck m) {
		this.m = m;
	}

	public void click(int x, int y, int width, int height) throws ClicException {
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
		
		verificareGataSiModificare(i, j);

		// trimit i si j

		// m.modify(i, j);
		if (send == false) {
			send = true;
			cm.sendMove(i, j);
			m.modify(i, j);
		} else
			System.out.println("ai dat click o data");

	}

	private void afisareMesajGata() {
		String rez = null;
		if (m.getMc().getScorjucator1() > m.getMc().getScorjucator2())
			rez = "Jucatorul 1 a castigat";
		else if (m.getMc().getScorjucator1() > m.getMc().getScorjucator2())
			rez = "Jucatorul 2 a castigat";
		else
			rez = "E egalitate";
		JOptionPane.showMessageDialog(null, "GAME OVER!\n" + rez);
	}

	public void newGame(int n, int m, int nrpozpunct) {
		this.m.initMatrix(n, m, nrpozpunct);
	}
	private void verificareGataSiModificare(int i, int j) {
		m.verifOver();
		if (m.getMc().isGata()) {
			afisareMesajGata();
		} else {
			try {
				m.modify(i, j);
				m.verifOver();
				if (m.getMc().isGata())
					afisareMesajGata();
			} catch (ClicException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
}
	}
