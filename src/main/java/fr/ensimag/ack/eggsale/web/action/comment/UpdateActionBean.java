/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.comment;

import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class UpdateActionBean extends BaseActionBean{
    
    private static String VIEW = VIEW_BASE_PATH + "comment/update.jsp";
    
    @EJB
    CommentFacade commentFacade;
    
    @Validate(required=true, on="save")
    private String content;
    
    @Validate(required=true, on={"save","form"})
    private Long commentId;
    
    private Long ideaId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }
	
    @DefaultHandler
    public Resolution form() {
        
       //Ensure user logged in
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the comment to update
        Comment comment = null;
        if(commentId != null) {
            comment = commentFacade.findById(commentId);
        }
        if(comment == null) {
            return new ErrorResolution(404, "Comment not found");
        }
        
        // Ensure user has the right to update
        User currentUser = this.getCurrentUser();
        User commentOwner = comment.getOwner();
        User ideaOwner = comment.getIdea().getOwner();
        User projectOwner = comment.getIdea().getProject().getOwner();
        if(!currentUser.equals(commentOwner) && !currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the comment
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't update this comment ");
            }
        }
        
        content = comment.getContent();
        ideaId = comment.getIdea().getId();
        
        return new ForwardResolution(VIEW);
    }
    
    public Resolution save() {
        
        //Ensure user logged in
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the comment to update
        Comment comment = commentFacade.findById(commentId);
        
        if(comment == null) {
            return new ErrorResolution(404, "Comment not found");
        }
        
        // Ensure user has the right to update
        User currentUser = this.getCurrentUser();
        User commentOwner = comment.getOwner();
        User ideaOwner = comment.getIdea().getOwner();
        User projectOwner = comment.getIdea().getProject().getOwner();
        if(!currentUser.equals(commentOwner) && !currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the comment
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't update this comment ");
            }
        }
        
        comment.setContent(content);
        
        commentFacade.persist(comment);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", comment.getIdea().getId());
        return res;
    }
     
}
