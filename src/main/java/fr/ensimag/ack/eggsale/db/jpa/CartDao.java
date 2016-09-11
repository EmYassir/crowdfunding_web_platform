package fr.ensimag.ack.eggsale.db.jpa;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CartFacade;
import java.util.List;
import javax.ejb.Singleton;

@Singleton(name = "CartFacade")
public class CartDao extends AbstractDao<Cart> implements CartFacade {

    public CartDao() {
        super(Cart.class);
        // TODO Auto-generated constructor stub
    }

 
    public Cart create(float amount, boolean validated, User owner,
            List<Project> projects) {
        Cart cart = new Cart();
        cart.setTotal(amount);
        cart.setIsValidate(validated);
        cart.setOwner(owner);
        cart.setProjects(projects);
        this.persist(cart);
        return cart;
    }
}
