package util;

import java.io.Serializable;

public class StartGame implements Serializable{
	private static final long serialVersionUID = 6385804034433829019L;
	
	private boolean start;
	private Integer n,m;
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
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
	@Override
	public String toString() {
		return "StartGame [start=" + start + ", n=" + n + ", m=" + m + "]";
	}
}
