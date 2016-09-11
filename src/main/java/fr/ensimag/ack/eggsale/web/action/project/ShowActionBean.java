package fr.ensimag.ack.eggsale.web.action.project;

import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.Base64;
import net.sourceforge.stripes.validation.Validate;

public class ShowActionBean extends BaseActionBean {

    public static String VIEW = VIEW_BASE_PATH + "project/show.jsp";
    @Validate(required = true)
    public Long id;
    
    private Project project;
    
    public Project getProject(){
        return project;
    } 
    
    private String imageContent;

    public String getimageContent() {
        return imageContent;
    }

    @EJB
    ProjectFacade projectFacade;

    @DefaultHandler
    public Resolution show() {
        project = projectFacade.findById(id);
        if(!project.getAttachments().isEmpty()){
            imageContent = Base64.encodeBytes(project.getAttachments().get(0).getContent());
        }
        return new ForwardResolution(VIEW);
    }
    
    
    public Resolution addToCart() {        
        //Ensure User LoggedIn
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        // Getting the cart
        SessionCart currentCart = getContext().getCurrentCart();
        
        //Getting the project to add
        Project project =  null;
        if(id != null) {
            project = projectFacade.findById(id);
        }
        if(project == null) {
            return new ErrorResolution(404,"The project was not found");
        }
        
        currentCart.addProduct(project);
        
        // Register the cart
        this.getContext().setCurrentCart(currentCart);
	
	this.getContext().getMessages().add(
		new LocalizableMessage("project.added"));
        
        return show();
    }
}