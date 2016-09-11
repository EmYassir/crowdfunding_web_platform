package fr.ensimag.ack.eggsale.db.dummy;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CartFacade;
import java.util.List;
import javax.annotation.PostConstruct;

public class DummyCartFacade extends DummyFacade<Cart> implements CartFacade {

    @PostConstruct
    public void createInitialCarts() {
    }

    public Cart create(float amount, boolean validated, User owner, List<Project> projects) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
