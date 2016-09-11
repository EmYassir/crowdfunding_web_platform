package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.db.MD5Util;
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

public class UpdateActionBean extends BaseActionBean {
    
    private static String VIEW = VIEW_BASE_PATH + "user/update.jsp";
    
    @Validate(mask="([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", on="updateData")
    public String email;
    public String username; 
    public String description;
    
    @Validate(required=true, on="updatePassword")
    private String password;
    public void setPassword(String p) { password = MD5Util.md5Hex(p); }
    @Validate(required=true, minlength=6, on="updatePassword")
    public String password2;
    
    @Validate(required=true, on="deleteAccount")
    public boolean confirmDeletion;
    
    @EJB
    UserFacade userFacade;

    @DefaultHandler
    @DontValidate
    public Resolution form() {
	User user = getCurrentUser();
	email = user.getEmail();
	username = user.getUserName();
	description = user.getDescription();
	return new ForwardResolution(VIEW); 
    }

    @ValidationMethod(on="updateData")
    public void verifyUpdateData() {
	ValidationErrors errors = getContext().getValidationErrors();
	//Si l'email n'est pas celui d'avant et qu'il exite déjà dans la base de donnée
	if (!getCurrentUser().getEmail().equals(email) &&
		userFacade.findByEmail(email) != null) {
	    errors.add("Existing email",
		    new LocalizableError("user.email.existing"));
	}	    
    }
    
    public Resolution updateData() {
	User user = getCurrentUser();
	userFacade.update(user, email, username, description);
	
	getContext().getMessages().add(
		new LocalizableMessage("user.updated", user.getUserName()));
	
        
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("userId", user.getId());
        return res;
    }
    
    @ValidationMethod(on="updatePassword")
    public void verifyUpdatePassword() {
	ValidationErrors errors = getContext().getValidationErrors();
	//Si la réauthentification n'est pas correct
	if (!password.equals(getCurrentUser().getPassword())) {
	    errors.add("Password incorrect",
		    new LocalizableError("user.password.incorrect"));
	}
    }
    
    public Resolution updatePassword() {
	User user = getCurrentUser();
	user.setPassword(password2);
	
	getContext().getMessages().add(
		new LocalizableMessage("user.updated", user.getUserName()));
	
        
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("userId", user.getId());
        return res;
    }
    
    public Resolution deleteAccount() {
	userFacade.drop(getCurrentUser());
    	this.getContext().logout();
	return new RedirectResolution(LoginActionBean.class);
    }
}
