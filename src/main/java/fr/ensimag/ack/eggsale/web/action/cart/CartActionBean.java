package fr.ensimag.ack.eggsale.web.action.cart;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class CartActionBean extends BaseActionBean{

    @DefaultHandler
    public Resolution projectcontroller() {
            return new ForwardResolution(VIEW_BASE_PATH + "cart/cart.jsp");
    }
    
}
