package fr.ensimag.ack.eggsale.web.action.cart;

import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class PayActionBean  extends BaseActionBean {
    private float amount;

    public float getAmount() {
        return amount;
    }

    
    @DefaultHandler
    public Resolution pay() {
        SessionCart cart=this.getContext().getCurrentCart();
        amount=cart.getAmount();
        ForwardResolution res = new ForwardResolution(VIEW_BASE_PATH + "payment/paypal.jsp");
        return res;
    }
}
