package fr.ensimag.ack.eggsale.web.action.cart;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class RemoveFromCartActionBean extends BaseActionBean {

    @EJB
    ProjectFacade projectFacade;
    
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    @DefaultHandler
    public Resolution remove() {
        
        //Ensure User LoggedIn
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        // Getting the cart
        SessionCart currentCart = getContext().getCurrentCart();
        
        //Getting the project to remove
        Project project =  null;
        if(projectId != null) {
            project = projectFacade.findById(projectId);
        }
        if(project == null) {
            return new ErrorResolution(404,"The project was not found");
        }
        
        //Removing the product from cart
        if(projectId != null) {
            currentCart.removeProduct(project);
        }
        
        // Register the new cart
        this.getContext().setCurrentCart(currentCart);

        return new RedirectResolution(fr.ensimag.ack.eggsale.web.action.cart.ShowActionBean.class);
    }
    
     public Resolution removeAll() {
        
        //Ensure User LoggedIn
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        // Getting the cart
        SessionCart currentCart = getContext().getCurrentCart();
        
        //Getting the project to remove
        Project project =  null;
        if(projectId != null) {
            project = projectFacade.findById(projectId);
        }
        if(project == null) {
            return new ErrorResolution(404,"The project was not found");
        }
        
        //Removing the product from cart
        if(projectId != null) {
            currentCart.removeAllProduct(project);
        }
        
        // Register the new cart
        this.getContext().setCurrentCart(currentCart);

        return new RedirectResolution(fr.ensimag.ack.eggsale.web.action.cart.ShowActionBean.class);
    }
     
     public Resolution flush() {
        
        //Ensure User LoggedIn
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        // Register the new cart
        this.getContext().setCurrentCart(new SessionCart());

        return new RedirectResolution(fr.ensimag.ack.eggsale.web.action.cart.ShowActionBean.class);
     }
     
}
