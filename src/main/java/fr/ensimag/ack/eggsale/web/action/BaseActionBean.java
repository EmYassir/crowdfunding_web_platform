package fr.ensimag.ack.eggsale.web.action;

import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.MyActionBeanContext;
import fr.ensimag.ack.eggsale.web.action.user.LoginActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class BaseActionBean implements ActionBean {

    protected static final String VIEW_BASE_PATH = "/WEB-INF/jsp/";
    private MyActionBeanContext context;
    @EJB
    private UserFacade userFacade;

    @Override
    public MyActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (MyActionBeanContext) context;
    }

    protected Resolution ensureLoggedIn() {
        if (this.getContext().getCurrentUserId() == 0) {
            return new RedirectResolution(LoginActionBean.class);
        } else {
            return null;
        }
    }

    public User getCurrentUser() {
        long id = this.getContext().getCurrentUserId();
        if (id == 0) {
            return null;
        }
        return userFacade.findById(id);
    }

    // Carte courante
    public SessionCart getCurrentCart() {
        SessionCart cart = this.getContext().getCurrentCart();
        return cart;
    }
    
    public String getPath() {
	return this.toString().split("@")[0];
    }
}
