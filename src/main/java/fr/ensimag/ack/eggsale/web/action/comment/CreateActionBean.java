/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.comment;

import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class CreateActionBean extends BaseActionBean{
    
    @EJB
    CommentFacade commentFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    IdeaFacade ideaFacade;
    
    @Validate(required=true, on="save")
    private String content;
    
    @Validate(required=true, on={"save","form"})
    private Long ideaId;
    
    private Long parentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    } 
	
    @DefaultHandler
    public Resolution form() {
            Resolution auth = this.ensureLoggedIn();
            if(auth != null) {
                return auth;
            }
            return new ForwardResolution(VIEW_BASE_PATH + "comment/form.jsp");
    }
    
    public Resolution save() {
        
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        User user = this.getCurrentUser();
        
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setOwner(user);
        comment.setIdea(ideaFacade.findById(ideaId));
        if(parentId != null) {
            comment.setParent(commentFacade.findById(parentId));
        }
        
        commentFacade.persist(comment);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", ideaId);
        return res;
    }
     
}
