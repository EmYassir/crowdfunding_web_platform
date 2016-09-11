/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.reward;

import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.Reward;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.db.facade.RewardFacade;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class CreateActionBean extends BaseActionBean{
    
    @EJB
    RewardFacade rewardFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    IdeaFacade ideaFacade;
    
    @Validate(required=true, on="save")
    private Float amount;
    
    @Validate(required=true, on={"save","form"})
    private Long ideaId;
    
    private Long parentId;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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
                return new ErrorResolution(403, "You can't reward this idea ");
            }
        }
        
        //Ensure the idea is not already rewarded
        if(idea.getReward() != null) {
            RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
            res.addParameter("ideaId", ideaId);
            return res;
        }
        
        return new ForwardResolution(VIEW_BASE_PATH + "reward/form.jsp");
    }
    
    public Resolution save() {
        
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
                return new ErrorResolution(403, "You can't reward this idea ");
            }
        }
        
        Reward reward = new Reward();
        reward.setIdea(idea);
        reward.setAmount(amount);
        
        rewardFacade.persist(reward);
        
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.idea.ShowActionBean.class);
        res.addParameter("ideaId", ideaId);
        return res;
    }
     
}
