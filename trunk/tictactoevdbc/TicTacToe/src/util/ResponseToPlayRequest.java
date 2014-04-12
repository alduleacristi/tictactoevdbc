package util;

import java.io.Serializable;

public class ResponseToPlayRequest implements Serializable {

	private static final long serialVersionUID = 7835153859936333758L;
	
	private boolean accept;
	private String player1,player2;
	private Integer n,m;
	
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	public Integer getM() {
		return m;
	}
	public void setM(Integer m) {
		this.m = m;
	}
}
