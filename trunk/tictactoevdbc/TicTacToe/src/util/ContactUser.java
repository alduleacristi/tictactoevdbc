package util;

import java.io.Serializable;

public class ContactUser implements Serializable {

	private static final long serialVersionUID = 2971735461964373723L;
	
	private String player2,player1;
	private Integer n,m;

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

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	
	
}
