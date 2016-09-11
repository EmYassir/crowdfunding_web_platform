/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.project;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.facade.AttachmentFacade;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

public class CreateActionBean extends BaseActionBean{
    
    public static String VIEW = VIEW_BASE_PATH + "project/create.jsp";
    
    @Validate(required=true, minlength=3)
    public String name; 
    @Validate(required=true)
    public float price;    
    @Validate(required=true, minlength=6)
    public String description;
    public boolean isSalable;  
    
    @EJB
    ProjectFacade projectFacade;
    
    private List<FileBean> newAttachments;
    
    @EJB
    AttachmentFacade attachmentFacade;

    public List<FileBean> getNewAttachments() {
        return newAttachments;
    }

    public void setNewAttachments(List<FileBean> newAttachments) {
        this.newAttachments = newAttachments;
    }
    
    @DefaultHandler
    @DontValidate
    public Resolution form(){
        Resolution auth = this.ensureLoggedIn();
        if (auth != null) {return auth;}
        return new ForwardResolution(VIEW);
    }
    
    public Resolution submit() {
        Resolution auth = this.ensureLoggedIn();
        if (auth != null) {return auth;}
        List<Attachment> attachments = new ArrayList<Attachment>();
        try {
            ValidationErrors errors = new ValidationErrors();
            if(newAttachments==null) {
                newAttachments = new ArrayList<FileBean>();
            }
            for(FileBean file : newAttachments) {
                if(file==null) {
                    continue;
                }
                if(file.getSize() > Attachment.maxLength) {
                    errors.add( "newAttachments", new SimpleError("File size is too big",null) );
                    break;
                }
                byte[] content = new byte[(int)file.getSize()];
                file.getInputStream().read(content);
                Attachment attachment = attachmentFacade.create(file.getFileName(), content);
                attachments.add(attachment);
                file.delete();
            }
            if(!errors.isEmpty()) {
                getContext().setValidationErrors(errors);
                return getContext().getSourcePageResolution();
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Project project = projectFacade.create(name, price, description, isSalable, this.getCurrentUser(), attachments);
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("id", project.getId());
        return res;
    }    
}
