package fr.ensimag.ack.eggsale.web.action.cart;

import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ShowActionBean extends BaseActionBean{
    
    @EJB
    ProjectFacade projectFacade;
    
    Map<Project,Integer> products;

    public Map<Project, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Project, Integer> products) {
        this.products = products;
    }
    
    
    
    @DefaultHandler
    public Resolution show() {
        
        //Ensure User LoggedIn
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        SessionCart cart = getContext().getCurrentCart();
        Map<Long,Integer> items = cart.getItems();
        
        products = new HashMap<Project,Integer>();
        for(Long projectId: cart.getItems().keySet()){
            Project project = projectFacade.findById(projectId);
            if(project != null) {
                products.put(project, cart.getItems().get(projectId));
            } else {
                items.remove(projectId);
            }
        }
        cart.setItems(items);
        this.getContext().setCurrentCart(cart);
        
        return new ForwardResolution(VIEW_BASE_PATH + "cart/show.jsp");
        
    }
}
