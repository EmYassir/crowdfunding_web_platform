package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class CreateActionBean extends BaseActionBean {
    
    private static String VIEW = VIEW_BASE_PATH + "user/create.jsp";
    
    @Validate(required=true, mask="([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", on="save")
    public String email;
    @Validate(required=true, minlength=6, on="save")
    public String password; 
    @Validate(required=true, minlength=6, on="save")
    public String password2; 
    @Validate(required=true)
    public String username; 
    public String description;
    
    @EJB
    UserFacade userFacade;

    @DefaultHandler
    @DontValidate
    public Resolution form() {
	return new ForwardResolution(VIEW); 
    }

    @ValidationMethod
    public void verify() {
	ValidationErrors errors = getContext().getValidationErrors();
	if (userFacade.findByEmail(email) != null) {
	    errors.add("Existing email", new LocalizableError("user.email.existing"));
	}
	
	if (!password.equals(password2)) {
	    errors.add("Password don't match", new LocalizableError("user.password.dontmatch"));
	}
    }
    
    public Resolution save() {	
	User user = userFacade.create(email, password, username, description);
	
	getContext().getMessages().add(
		new LocalizableMessage("user.created", user.getUserName()));
    	getContext().setCurrentUserId(user.getId());
	
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("userId", user.getId());
        return res;
    }
}
