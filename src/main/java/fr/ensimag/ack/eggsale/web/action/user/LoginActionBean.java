package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.db.MD5Util;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class LoginActionBean extends BaseActionBean {
    
    private static String VIEW = VIEW_BASE_PATH + "user/login.jsp";
    
    private User user;

    @Validate(required=true)
    private String email;

    @Validate(required=true)
    private String password;

    @EJB
    UserFacade userFacade;
	
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = MD5Util.md5Hex(password);
    }

    @DefaultHandler
    @DontValidate
    public Resolution form() {
        return new ForwardResolution(VIEW);
    }
    
    // This runs after the property validations, so pseudo and password are for sure not null  
    @ValidationMethod
    public void authenticate() {
	user = userFacade.authenticate(email, password);
	if (user == null) {
	    ValidationErrors errors = getContext().getValidationErrors();
	    errors.add("invalidpassword", new LocalizableError("user.authentication.invalidpassword"));
	}
    }
	
    public Resolution login() {
    	this.getContext().setCurrentUserId(user.getId());
        
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("userId", user.getId());
        return res;
    }   
}
