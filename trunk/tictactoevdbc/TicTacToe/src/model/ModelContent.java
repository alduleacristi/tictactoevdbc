package model;

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
		pointsList = new ArrayList<Point>();
	}

	public int getIJElement(int i, int j) {
		return a.get(i).get(j);
	}

	public void setIJElement(int i, int j, int val) {
		a.get(i).set(j, val);
	}

	public int getLinii() {
		return a.size();
	}

	public int getColoane() {
		return a.get(0).size();
	}

	public void addPeLinii(List<Integer> aux) {
		a.add(aux);
	}

	public void addPeColoane(int i, int val) {
		a.get(i).add(val);
	}

	public int getJucator() {
		return jucator;
	}

	public void setJucator(int jucator) {
		this.jucator = jucator;
	}

	public int getScorjucator1() {
		return scorjucator1;
	}

	public void setScorjucator1(int scorjucator1) {
		this.scorjucator1 = scorjucator1;
	}

	public int getScorjucator2() {
		return scorjucator2;
	}

	public void setScorjucator2(int scorjucator2) {
		this.scorjucator2 = scorjucator2;
	}

	public int getNrpozpunct() {
		return nrpozpunct;
	}

	public void setNrpozpunct(int nrpozpunct) {
		this.nrpozpunct = nrpozpunct;
	}

	public boolean isPrimu() {
		return primu;
	}

	public void setPrimu(boolean primu) {
		this.primu = primu;
	}

	public boolean isJocnou() {
		return jocnou;
	}

	public void setJocnou(boolean jocnou) {
		this.jocnou = jocnou;
	}

	public List<Point> getPointsList() {
		return pointsList;
	}

	public void setPointsList(List<Point> pointsList) {
		this.pointsList = pointsList;
	}

	public void addPoint(Point point) {
		pointsList.add(point);
	}

	public boolean isGata() {
		return gata;
	}

	public void setGata(boolean gata) {
		this.gata = gata;
	}

	public void notifyObs() {
		//System.out.println("notify obs");
		setChanged();
		notifyObservers();
	}

}
