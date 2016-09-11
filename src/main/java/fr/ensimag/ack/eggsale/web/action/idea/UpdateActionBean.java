package fr.ensimag.ack.eggsale.web.action.idea;

import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.AttachmentFacade;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

public class UpdateActionBean extends BaseActionBean{
    
    @EJB
    IdeaFacade ideaFacade;
    @EJB
    ProjectFacade projectFacade;
    @EJB
    AttachmentFacade attachmentFacade;
    
    @Validate(required=true, on="save")
    private String title;
    
    @Validate(required=true, on="save")
    private String content;
    
    private List<Attachment> attachmentsSaved;
    private List<Long> attachmentsToSave;
    
    private List<FileBean> newAttachments;
    
    @Validate(required=true, on={"form","save"})
    private Long ideaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<FileBean> getNewAttachments() {
        return newAttachments;
    }

    public void setNewAttachments(List<FileBean> newAttachments) {
        this.newAttachments = newAttachments;
    }
    
    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
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
    public Resolution form() {
        
        //Ensure user logged in
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the idea to update
        Idea idea = ideaFacade.findById(ideaId);
        if(idea == null) {
            return new ErrorResolution(404, "Idea not found");
        }
        
        // Ensure user has the right to update
        User currentUser = this.getCurrentUser();
        User ideaOwner = idea.getOwner();
        User projectOwner = idea.getProject().getOwner();
        if(!currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the idea
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't update this idea ");
            }
        }
        
        title = idea.getTitle();
        content = idea.getContent();
        attachmentsSaved = idea.getAttachments();
        
        return new ForwardResolution(VIEW_BASE_PATH + "idea/update.jsp");
    }
    
    
    public Resolution save() {
        
        //Ensure user logged in
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the idea to update
        Idea idea = ideaFacade.findById(ideaId);
        if(idea == null) {
            return new ErrorResolution(404, "Idea not found");
        }
        
        // Ensure user has the right to update
        User currentUser = this.getCurrentUser();
        User ideaOwner = idea.getOwner();
        User projectOwner = idea.getProject().getOwner();
        if(!currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the idea
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't update this idea ");
            }
        }
        
        
        
        List<Attachment> attachments = new ArrayList<Attachment>();
        
        if(attachmentsToSave==null) {
            attachmentsToSave = new ArrayList<Long>();
        }
        for(Long attach: attachmentsToSave) {
            if(attach == null) {
                continue;
            }
            System.out.println("Attach sel = " + attach);
            Attachment a = attachmentFacade.findById(attach);
            if(a.getIdea().getId() != ideaId) {
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
                Attachment attachment = new Attachment();
                attachment.setFileName(file.getFileName());
                attachment.setIdea(idea);
                attachment.setContent(content);
                attachments.add(attachment);
                file.delete();
            }
            if(!errors.isEmpty()) {
                getContext().setValidationErrors(errors);
                return getContext().getSourcePageResolution();
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdateActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        idea.setTitle(title);
        idea.setContent(content);
        
        List<Attachment> removeAttachments = new ArrayList<Attachment>(idea.getAttachments());
        removeAttachments.removeAll(attachments);
        
        for(Attachment attach: removeAttachments) {
            attachmentFacade.drop(attach);
        }
        idea.setAttachments(attachments);

        ideaFacade.persist(idea);

        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", idea.getId());
        return res;
    }
     
}
