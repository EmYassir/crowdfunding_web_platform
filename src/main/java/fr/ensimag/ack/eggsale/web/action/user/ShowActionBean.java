package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class ShowActionBean extends BaseActionBean {
    
    private static String VIEW = VIEW_BASE_PATH + "user/show.jsp";

    @Validate(required = true)
    public Long userId;
    private User user;
    public User getUser() { return user; }

    @EJB
    UserFacade userFacade;
    
    @DefaultHandler
    public Resolution show() {
	user = userFacade.findById(userId);
	return new ForwardResolution(VIEW);
    }
}
