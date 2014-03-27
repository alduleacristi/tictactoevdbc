package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ModelContent extends Observable {
	private List<List<Integer>> a;
	private int jucator, scorjucator1, scorjucator2, nrpozpunct;
	private boolean primu, jocnou;
	private List<Point> pointsList;
	private boolean gata;

	public ModelContent() {
		a = new ArrayList<List<Integer>>();
		gata = false;
		jucator = 1;
		scorjucator1 = 0;
		scorjucator2 = 0;
	}

	public List<List<Integer>> getA() {
		return a;
	}

	public void setA(List<List<Integer>> a) {
		this.a = a;
		setChanged();
		notifyObservers();
	}

	public int getJucator() {
		return jucator;
	}

	public void setJucator(int jucator) {
		this.jucator = jucator;
		setChanged();
		notifyObservers();
	}

	public int getScorjucator1() {
		return scorjucator1;
	}

	public void setScorjucator1(int scorjucator1) {
		this.scorjucator1 = scorjucator1;
		setChanged();
		notifyObservers();
	}

	public int getScorjucator2() {
		return scorjucator2;
	}

	public void setScorjucator2(int scorjucator2) {
		this.scorjucator2 = scorjucator2;
		setChanged();
		notifyObservers();
	}

	public int getNrpozpunct() {
		return nrpozpunct;
	}

	public void setNrpozpunct(int nrpozpunct) {
		this.nrpozpunct = nrpozpunct;
		setChanged();
		notifyObservers();
	}

	public boolean isPrimu() {
		return primu;
	}

	public void setPrimu(boolean primu) {
		this.primu = primu;
		setChanged();
		notifyObservers();
	}

	public boolean isJocnou() {
		return jocnou;
	}

	public void setJocnou(boolean jocnou) {
		this.jocnou = jocnou;
		setChanged();
		notifyObservers();
	}

	public List<Point> getPointsList() {
		return pointsList;
	}

	public void setPointsList(List<Point> pointsList) {
		this.pointsList = pointsList;
		setChanged();
		notifyObservers();
	}

	public boolean isGata() {
		return gata;
	}

	public void setGata(boolean gata) {
		this.gata = gata;
		setChanged();
		notifyObservers();
	}

}
