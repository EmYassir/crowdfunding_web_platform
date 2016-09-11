package fr.ensimag.ack.eggsale.web.action.cart;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CartFacade;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import java.util.ArrayList;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ValidateTransactionActionBean  extends BaseActionBean {
    
    public static String VIEW = VIEW_BASE_PATH + "payment/paypal_return.jsp";
    
    @EJB
    CartFacade cartFacade;
   
    @EJB
    UserFacade userFacade;
    
    @EJB
    ProjectFacade projectFacade;
       
    @DefaultHandler
    public Resolution redirect() {
        // Enregistrement dans la base de donnees
        User user = this.getCurrentUser();
        SessionCart sessionCart = this.getContext().getCurrentCart();
        
        // Creation d'une carte
        
        // Liste des produits
        ArrayList<Project> list = new ArrayList<Project>();
        
        for(Long projectId: sessionCart.getItems().keySet()) {
            Project project = projectFacade.findById(projectId);
            for(int i = 1;i<=sessionCart.getItems().get(projectId);i++) {
                list.add(project);
            }
        }
        
        // Ajout dans la BD
        Cart cart = cartFacade.create(sessionCart.getAmount(), true, user, list);
        userFacade.updateUserCarts(user.getId(), cart);
        
        // Ecrasement de la carte en session
        sessionCart=new SessionCart();
        this.getContext().setCurrentCart(sessionCart);
  
        // Redirection
        return new ForwardResolution(VIEW);
    }
}
