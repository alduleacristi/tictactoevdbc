package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.User;
import model.Game;

public class DataBase {
	private Connection con = null;
	private Statement stmt = null;
	private String serverName, portNumber = "3306", username, password;
	private Properties connProperties;
	private String sql;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	public DataBase() throws SQLException {
		this.username = "root";
		this.password = "";
		this.serverName = "localhost";
		this.portNumber = "3306";
		this.connProperties = new java.util.Properties();
		connProperties.put("user", this.username);
		connProperties.put("password", this.password);

		createConexion();
	}

	/**
	 * Establish the connection with the database
	 * 
	 * @throws SQLException
	 */
	private void createConexion() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/", connProperties);
	}

	/**
	 * Insert an user in the database
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void insert(User user) throws SQLException {
		if (user == null)
			throw new SQLException("Invalid insert. Null object.");
		sql = "insert into user(username,password) values(?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the user for the id specified as parameter.
	 * 
	 * @param id
	 * @return user
	 * @throws SQLException
	 */
	public User getUser(int id) throws SQLException {
		stmt = con.createStatement();
		sql = "Select username, password from user where id = " + id + "";
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			User user = new User();
			user.setIdUser(id);
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			return user;
		}
		return null;
	}

	/**
	 * Returns the id for the user with the username sent as parameter.
	 * 
	 * @param username
	 * @return id
	 * @throws SQLException
	 */
	public int getUserId(String username) throws SQLException {
		stmt = con.createStatement();
		sql = "Select id from user where username = " + username + "";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id;
			id = rs.getInt(1);
			return id;
		}
		return -1;
	}

	/**
	 * Create Game function Status should be left as default "In progress".
	 * Winner,beginDate and endDate also should be left null until update.
	 * 
	 * Just set in game idPlayer1, idPlayer2 and state.
	 * 
	 * @param game
	 * @throws SQLException
	 */
	public void insert(Game game) throws SQLException {
		if (game == null)
			throw new SQLException("Invalid insert. Null object.");
		sql = "insert into game(idPlayer1, idPlayer2, status, state) values(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, game.getUser2().getIdUser());
			pstmt.setInt(2, game.getUser3().getIdUser());
			pstmt.setBytes(3, game.getState());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the game with a specified id
	 * 
	 * @param id
	 * @return game
	 * @throws SQLException
	 */
	public Game getGame(int id) throws SQLException {
		stmt = con.createStatement();
		sql = "Select * from game where id = " + id + "";
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Game game = new Game();
			// game.setUser1(); //to be continued :D
			return game;
		}
		return null;
	}

	@Override
	public void finalize() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
