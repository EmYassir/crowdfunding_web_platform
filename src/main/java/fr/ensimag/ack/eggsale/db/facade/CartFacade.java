package fr.ensimag.ack.eggsale.db.facade;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.User;
import java.util.List;

public interface CartFacade extends BaseFacade<Cart> {
        public Cart create(float amount, boolean validated, User owner,
            List<Project> projects);
}
