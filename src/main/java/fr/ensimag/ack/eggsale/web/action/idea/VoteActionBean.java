/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.idea;

import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import java.util.List;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class VoteActionBean extends BaseActionBean{
    
    @EJB
    IdeaFacade ideaFacade;
    
    private Long ideaId;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }
    
	
    public Resolution voteUp() {
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        Idea idea = null;
        if(ideaId != null) {
            idea = ideaFacade.findById(ideaId);
        }
        if(idea == null) {
            return new ErrorResolution(404,"The idea does not exists");
        }
        
        User currentUser = this.getCurrentUser();
        
        if(idea.getVotedUp().contains(currentUser)) {
            List<User> votedUp = idea.getVotedUp();
            votedUp.remove(currentUser);
            idea.setVotedUp(votedUp);
        } else {
            List<User> votedUp = idea.getVotedUp();
            votedUp.add(currentUser);
            idea.setVotedUp(votedUp);
        }
        
        ideaFacade.persist(idea);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", idea.getId());
        return res;
    }
    
    
    public Resolution voteDown() {
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        Idea idea = null;
        if(ideaId != null) {
            idea = ideaFacade.findById(ideaId);
        }
        if(idea == null) {
            return new ErrorResolution(404,"The idea does not exists");
        }
        
        User currentUser = this.getCurrentUser();
        
        List<User> votedDown = idea.getVotedDown();
        if(idea.getVotedDown().contains(currentUser)) {
            votedDown.remove(currentUser);
            idea.setVotedDown(votedDown);
        } else {
            votedDown.add(currentUser);
            idea.setVotedDown(votedDown);
        }
        
        ideaFacade.persist(idea);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", idea.getId());
        return res;
    }
     
}
