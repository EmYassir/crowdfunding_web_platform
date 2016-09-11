package fr.ensimag.ack.eggsale.db.jpa;

import javax.ejb.Singleton;

import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.facade.AdministratorFacade;

@Singleton(name = "AdministratorFacade")
public class AdministratorDao extends AbstractDao<Administrator> implements AdministratorFacade {

	public AdministratorDao() {
		super(Administrator.class);
	}

}
