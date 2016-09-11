package fr.ensimag.ack.eggsale.db.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProducer {
	@Produces
	@ApplicationScoped
	public EntityManagerFactory create() {
		return Persistence.createEntityManagerFactory("eggsalejpa");
	}

	public void destroy(@Disposes EntityManagerFactory factory) {
		factory.close(); // 5
	}
}
