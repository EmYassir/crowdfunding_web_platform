package fr.ensimag.ack.eggsale.web.action.cart;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CartFacade;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class CreateActionBean extends BaseActionBean{
    
    private User user;
    
    private Cart cart = new Cart();

    
    @EJB
    CartFacade cartFacade;
    
        
    @EJB
    UserFacade userFacade;
    
    
    public Cart getCart(){
        return user.getCurrentOrder();
    }
    
    
	
    @DefaultHandler
    public Resolution save() {
            user = this.getCurrentUser();
            cart.setOwner(user);
            user.setCurrentOrder(cart);
            cartFacade.persist(cart);
            userFacade.persist(user);
            /*RedirectResolution res = new RedirectResolution(ShowActionBean.class);
            res.addParameter("cart", cart);
            return res; */
            return null;
    }
}
