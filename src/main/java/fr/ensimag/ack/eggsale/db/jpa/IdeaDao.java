package fr.ensimag.ack.eggsale.db.jpa;

import javax.ejb.Singleton;

import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;

@Singleton(name = "IdeaFacade")
public class IdeaDao extends AbstractDao<Idea> implements IdeaFacade {

	public IdeaDao() {
		super(Idea.class);
		// TODO Auto-generated constructor stub
	}

}
