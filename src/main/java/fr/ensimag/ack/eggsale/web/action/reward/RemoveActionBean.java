/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.reward;

import fr.ensimag.ack.eggsale.web.action.comment.*;
import fr.ensimag.ack.eggsale.web.action.idea.*;
import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.Reward;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.db.facade.RewardFacade;
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
    RewardFacade rewardFacade;
    @EJB
    IdeaFacade ideaFacade;
    
    private Long ideaId;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }
    
    
    
    @DefaultHandler
    public Resolution delete() {
        
        //Ensure user logged in
        Resolution auth = this.ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        Idea idea = null;
        // Ensure the idea exists
        if(ideaId != null) {
            idea = ideaFacade.findById(ideaId);
        }
        if(idea == null) {
            return new ErrorResolution(404,"The idea does not exists");
        }
            
        // Ensure user has the right to reward
        User currentUser = this.getCurrentUser();
        User projectOwner = idea.getProject().getOwner();
        if(!currentUser.equals(projectOwner)) {
            //if not admin and not owner of the comment
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't unreward this idea ");
            }
        }
        
        // Remove the reward
        Reward reward = idea.getReward();
        idea.setReward(null);
        
        ideaFacade.persist(idea);
        rewardFacade.drop(reward);
        
        // Return to project's page
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId",idea.getId());
        return res;
    }
}
