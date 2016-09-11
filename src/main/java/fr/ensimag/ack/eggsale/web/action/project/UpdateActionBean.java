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
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Pedro
 */
public class UpdateActionBean extends BaseActionBean{
    
    public static String VIEW = VIEW_BASE_PATH + "project/update.jsp";
    
    public Long id;
    
    @Validate(required=true, minlength=3)
    public String name; 
    @Validate(required=true)
    public float price;    
    @Validate(required=true, minlength=6)
    public String description;
    public boolean isSalable;  
    
    @EJB
    ProjectFacade projectFacade;
    @EJB
    AttachmentFacade attachmentFacade;
    
    private List<Attachment> attachmentsSaved;
    private List<Long> attachmentsToSave;
    
    private List<FileBean> newAttachments;

    public List<FileBean> getNewAttachments() {
        return newAttachments;
    }

    public void setNewAttachments(List<FileBean> newAttachments) {
        this.newAttachments = newAttachments;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public List<Attachment> getAttachmentsSaved() {
        return attachmentsSaved;
    }

    public void setAttachmentsSaved(List<Attachment> attachmentsSaved) {
        this.attachmentsSaved = attachmentsSaved;
    }

    public List<Long> getAttachmentsToSave() {
        return attachmentsToSave;
    }

    public void setAttachmentsToSave(List<Long> attachmentsToSave) {
        this.attachmentsToSave = attachmentsToSave;
    }
    
    @DefaultHandler
    @DontValidate
    public Resolution form(){
        Resolution auth = this.ensureLoggedIn();
        if (auth != null) {return auth;}
        Project project = projectFacade.findById(id);
        name = project.getName();
        price = project.getPrice();
        description = project.getDescription();
        isSalable = project.isIsSalable();
        attachmentsSaved = project.getAttachments();        
        return new ForwardResolution(VIEW);        
    }
    
    public Resolution update(){        
        Resolution auth = this.ensureLoggedIn();
        if (auth != null) {return auth;}List<Attachment> attachments = new ArrayList<Attachment>();
        
        //Getting the idea to update
        Project project = projectFacade.findById(id);
        if(project == null) {
            return new ErrorResolution(404, "Idea not found");
        }
        if(attachmentsToSave==null) {
            attachmentsToSave = new ArrayList<Long>();
        }
        for(Long attach: attachmentsToSave) {
            if(attach == null) {
                continue;
            }
            System.out.println("Attach sel = " + attach);
            Attachment a = attachmentFacade.findById(attach);
            if(a.getProject().getId() != id) {
                //validationError
                ValidationErrors errors = new ValidationErrors();
                errors.add( "attachmentsToSave", new SimpleError("A selected saved file is not the property of this idea",null) );
                getContext().setValidationErrors(errors);
                return getContext().getSourcePageResolution();
            }
            attachments.add(a);
        }

        
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
                attachment.setProject(project);
                attachments.add(attachment);
                file.delete();
            }
            if(!errors.isEmpty()) {
                getContext().setValidationErrors(errors);
                return getContext().getSourcePageResolution();
            }
        } catch (IOException ex) {
            Logger.getLogger(fr.ensimag.ack.eggsale.web.action.idea.UpdateActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        List<Attachment> removeAttachments = new ArrayList<Attachment>(project.getAttachments());
        removeAttachments.removeAll(attachments);
        
        for(Attachment attach: removeAttachments) {
            attachmentFacade.drop(attach);
        }
        projectFacade.update(id,name, price, description, isSalable,attachments);
        RedirectResolution res = new RedirectResolution(ShowActionBean.class);
        res.addParameter("id", id);
        return res;
    }
    
}
