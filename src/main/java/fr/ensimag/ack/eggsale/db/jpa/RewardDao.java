package fr.ensimag.ack.eggsale.db.jpa;

import javax.ejb.Singleton;

import fr.ensimag.ack.eggsale.db.entity.Reward;
import fr.ensimag.ack.eggsale.db.facade.RewardFacade;

@Singleton(name = "RewardFacade")
public class RewardDao extends AbstractDao<Reward> implements RewardFacade {

	public RewardDao() {
		super(Reward.class);
	}

}
