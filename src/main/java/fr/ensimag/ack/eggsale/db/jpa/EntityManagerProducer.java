package fr.ensimag.ack.eggsale.db.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerProducer {

	@Inject
	EntityManagerFactory emf; // 1

	@Produces
	@RequestScoped
	// 2
	public EntityManager create() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	public void destroy(@Disposes EntityManager em) {
		em.getTransaction().commit();
		em.close();
	}
}
