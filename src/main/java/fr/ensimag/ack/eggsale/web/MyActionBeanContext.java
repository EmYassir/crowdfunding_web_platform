package fr.ensimag.ack.eggsale.web;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import net.sourceforge.stripes.action.ActionBeanContext;

public class MyActionBeanContext extends ActionBeanContext {
    
    public long getCurrentUserId() {
        Object val = getRequest().getSession().getAttribute("userId");
        if (val == null) {
            return 0;
        }
        return (Long) val;
    }

    
    // Recuperation du panier de la session courante
    public SessionCart getCurrentCart() {
        Object cart = getRequest().getSession().getAttribute("currentCart");
        if(cart == null){
            cart = new SessionCart();
        }
        return (SessionCart)cart;
    }
    
    // Suppression de la carte
    public void rmCurrentCart() {
        getRequest().getSession().removeAttribute("currentCart");
    }
    
    public void setCurrentUserId(long userId) {
        getRequest().getSession().setAttribute("userId", (Long) userId);
    }
    
    // Enregistrement de cart dans la session courante
    public void setCurrentCart(SessionCart cart) {
        getRequest().getSession().setAttribute("currentCart", cart);
    }
    
    
    public void logout() {
        getRequest().getSession().invalidate();
    }
}
