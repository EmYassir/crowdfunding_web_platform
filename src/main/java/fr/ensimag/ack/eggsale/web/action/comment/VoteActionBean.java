/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.comment;

import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import java.util.List;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class VoteActionBean extends BaseActionBean{
    
    @EJB
    CommentFacade commentFacade;
    
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
	
    public Resolution voteUp() {
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        Comment comment = null;
        if(commentId != null) {
            comment = commentFacade.findById(commentId);
        }
        if(comment == null) {
            return new ErrorResolution(404,"The comment does not exists");
        }
        
        User currentUser = this.getCurrentUser();
        
        if(comment.getVotedUp().contains(currentUser)) {
            List<User> votedUp = comment.getVotedUp();
            votedUp.remove(currentUser);
            comment.setVotedUp(votedUp);
        } else {
            List<User> votedUp = comment.getVotedUp();
            votedUp.add(currentUser);
            comment.setVotedUp(votedUp);
        }
        
        commentFacade.persist(comment);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", comment.getIdea().getId());
        return res;
    }
    
    
    public Resolution voteDown() {
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        Comment comment = null;
        if(commentId != null) {
            comment = commentFacade.findById(commentId);
        }
        if(comment == null) {
            return new ErrorResolution(404,"The comment does not exists");
        }
        
        User currentUser = this.getCurrentUser();
        
        List<User> votedDown = comment.getVotedDown();
        if(comment.getVotedDown().contains(currentUser)) {
            votedDown.remove(currentUser);
            comment.setVotedDown(votedDown);
        } else {
            votedDown.add(currentUser);
            comment.setVotedDown(votedDown);
        }
        
        commentFacade.persist(comment);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", comment.getIdea().getId());
        return res;
    }
     
}
