package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idGame;

	private Timestamp beginDate;

	private Timestamp endDate;

	@Lob
	private byte[] state;

	public Game(Timestamp beginDate, Timestamp endDate, byte[] state,
			String status, User user1, User user2, User user3) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.state = state;
		this.status = status;
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
	}

	private String status;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "Winner")
	private User user1;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "idPlayer1")
	private User user2;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "idPlayer2")
	private User user3;

	public Game() {
	}

	public int getIdGame() {
		return this.idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the matrix in byte format.
	 * 
	 * @return state
	 */
	public byte[] getState() {
		return this.state;
	}

	/**
	 * Sets the matrix already mapped in byte[].
	 * 
	 * @param state
	 */
	public void setState(byte[] state) {
		this.state = state;
	}

	/**
	 * Returns the status, which could be "In progress" or "Finished".
	 * 
	 * @return
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status, which could be "In progress" or "Finished".
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return Winner
	 */
	public User getUser1() {
		return this.user1;
	}

	/**
	 * 
	 * @param Winner
	 */
	public void setUser1(User user1) { // asta e Winner
		this.user1 = user1;
	}

	/**
	 * 
	 * @return Player1
	 */
	public User getUser2() { // asta e Player1
		return this.user2;
	}

	/**
	 * 
	 * @param Player1
	 */
	public void setUser2(User user2) {
		this.user2 = user2;
	}

	/**
	 * 
	 * @return Player2
	 */
	public User getUser3() { // asta e Player2
		return this.user3;
	}

	/**
	 * 
	 * @param Player2
	 */
	public void setUser3(User user3) {
		this.user3 = user3;
	}

	@Override
	public String toString() {
		return "Game [idGame=" + idGame + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", state=" + Arrays.toString(state)
				+ ", status=" + status + ", user1=" + user1 + ", user2="
				+ user2 + ", user3=" + user3 + "]";
	}

	
}