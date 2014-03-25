package gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModelContent extends Observable{
	private List<List<Integer>> a;	
	private int jucator,scorjucator1,scorjucator2,nrpozpunct;
	private boolean primu,jocnou;
	private List<Point> pointsList;
	private boolean gata;
	
	
	public ModelContent() {
		gata = false;
	}
	
	public List<List<Integer>> getA() {
		return a;
	}

	public void setA(List<List<Integer>> a) {
		this.a = a;
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

	public boolean isGata() {
		return gata;
	}
	
	public void setGata(boolean gata) {
		this.gata = gata;
	}
	
}
