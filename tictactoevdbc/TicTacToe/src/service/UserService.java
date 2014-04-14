package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import model.User;
import exception.UserNotFoundException;

public class UserService {

	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("TicTacToe");
	private final EntityManager em = emf.createEntityManager();

	public void insertUser(User user) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) throws Exception {
		List<User> list = em.createNamedQuery(User.userByUsername)
				.setParameter("user", username).getResultList();
		if (list.size() == 0)
			throw new UserNotFoundException();
		return list.get(0);
	}
}
