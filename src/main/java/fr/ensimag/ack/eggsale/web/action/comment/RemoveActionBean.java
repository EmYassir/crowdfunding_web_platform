/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.comment;

import fr.ensimag.ack.eggsale.web.action.idea.*;
import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author Abderrahmane Boustelitane
 */
public class RemoveActionBean extends BaseActionBean{
    
    @EJB
    CommentFacade commentFacade;
    
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    
    
    @DefaultHandler
    public Resolution delete() {
        
        //Ensure user logged in
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the comment to remove
        Comment comment = null;
        if(commentId != null) {
            comment = commentFacade.findById(commentId);
        }
        if(comment == null) {
            return new ErrorResolution(404, "Comment not found");
        }
        
        // Ensure user has the right to remove
        User currentUser = this.getCurrentUser();
        User commentOwner = comment.getOwner();
        User ideaOwner = comment.getIdea().getOwner();
        User projectOwner = comment.getIdea().getProject().getOwner();
        if(!currentUser.equals(commentOwner) && !currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the comment
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't remove this comment ");
            }
        }
        
        // Remove the comment
        commentFacade.drop(comment);
        
        // Return to project's page
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId",comment.getIdea().getId());
        return res;
    }
}
