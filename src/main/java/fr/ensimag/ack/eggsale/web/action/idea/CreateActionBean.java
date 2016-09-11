package fr.ensimag.ack.eggsale.web.action.idea;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.User;
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
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

public class CreateActionBean extends BaseActionBean{
    
    @EJB
    IdeaFacade ideaFacade;
    @EJB
    ProjectFacade projectFacade;
    
    @Validate(required=true, on="save")
    private String title;
    
    @Validate(required=true, on="save")
    private String content;
    
    @Validate(required=true, on={"form","save"})
    private Long projectId;
    
    private List<FileBean> newAttachments;

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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<FileBean> getNewAttachments() {
        return newAttachments;
    }

    public void setNewAttachments(List<FileBean> newAttachments) {
        this.newAttachments = newAttachments;
    }
    
    
    @DefaultHandler
    public Resolution form() {
            Resolution auth = this.ensureLoggedIn();
            if(auth != null) {
                return auth;
            }
            return new ForwardResolution(VIEW_BASE_PATH + "idea/form.jsp");
    }
    
    
    public Resolution save() {
            Resolution auth = this.ensureLoggedIn();
            if(auth != null) {
                return auth;
            }
            
            User user = this.getCurrentUser();
            
            Project project = projectFacade.findById(projectId);
            
            Idea idea = new Idea();
            
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
                Logger.getLogger(CreateActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            idea.setTitle(title);
            idea.setContent(content);
            idea.setOwner(user);
            idea.setProject(project);
            idea.setAttachments(attachments);
            
            ideaFacade.persist(idea);
                     
            RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
            res.addParameter("ideaId", idea.getId());
            return res;
    }
     
}
