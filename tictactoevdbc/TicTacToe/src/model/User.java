package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUser;

	private String password;

	private String username;

	// bi-directional many-to-one association to Game
	@OneToMany(mappedBy = "user1", fetch = FetchType.EAGER)
	private List<Game> games1;

	// bi-directional many-to-one association to Game
	@OneToMany(mappedBy = "user2", fetch = FetchType.EAGER)
	private List<Game> games2;

	// bi-directional many-to-one association to Game
	@OneToMany(mappedBy = "user3", fetch = FetchType.EAGER)
	private List<Game> games3;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Game> getGames1() {
		return this.games1;
	}

	public void setGames1(List<Game> games1) {
		this.games1 = games1;
	}

	public Game addGames1(Game games1) {
		getGames1().add(games1);
		games1.setUser1(this);

		return games1;
	}

	public Game removeGames1(Game games1) {
		getGames1().remove(games1);
		games1.setUser1(null);

		return games1;
	}

	public List<Game> getGames2() {
		return this.games2;
	}

	public void setGames2(List<Game> games2) {
		this.games2 = games2;
	}

	public Game addGames2(Game games2) {
		getGames2().add(games2);
		games2.setUser2(this);

		return games2;
	}

	public Game removeGames2(Game games2) {
		getGames2().remove(games2);
		games2.setUser2(null);

		return games2;
	}

	public List<Game> getGames3() {
		return this.games3;
	}

	public void setGames3(List<Game> games3) {
		this.games3 = games3;
	}

	public Game addGames3(Game games3) {
		getGames3().add(games3);
		games3.setUser3(this);

		return games3;
	}

	public Game removeGames3(Game games3) {
		getGames3().remove(games3);
		games3.setUser3(null);

		return games3;
	}

}