package fr.ensimag.ack.eggsale.web.action.cart;

import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;


public class CancelTransactionActionBean  extends BaseActionBean {
    public static String VIEW = VIEW_BASE_PATH + "payment/paypal_cancel_return.jsp";
       
    @DefaultHandler
    public Resolution redirect() {
        // Redirection
        return new ForwardResolution(VIEW);
    }
}
