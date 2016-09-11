package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.action.HomeActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class LogoutActionBean extends BaseActionBean {
    
    @DefaultHandler
    public Resolution logout() {
    	this.getContext().logout();
        RedirectResolution res = new RedirectResolution(HomeActionBean.class);
        return res;
    }   
}
