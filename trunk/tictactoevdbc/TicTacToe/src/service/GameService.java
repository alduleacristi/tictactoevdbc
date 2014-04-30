package service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Game;
import model.User;

public class GameService {

	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("TicTacToe");
	private final EntityManager em = emf.createEntityManager();

	public void insertGame(User u1, User u2) {

		try {
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			List<Integer> list = new ArrayList<>();
			oos.writeObject(list);
			byte[] bytes = bos.toByteArray();
			Timestamp ts = new Timestamp(Calendar.getInstance()
					.getTimeInMillis());
			Game game = new Game(ts, null, bytes, "in progress", null, u1, u2);
			em.persist(game);
			em.flush();
			tr.commit();
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
}
