/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.idea;

import fr.ensimag.ack.eggsale.db.entity.Administrator;
import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.entity.User;
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
        Resolution auth = ensureLoggedIn();
        if(auth != null) {
            return auth;
        }
        
        //Getting the idea to remove
        Idea idea = null;
        if(ideaId != null) {
            idea = ideaFacade.findById(ideaId);
        }
        if(idea == null) {
            return new ErrorResolution(404, "Idea not found");
        }
        
        // Ensure user has the right to remove
        User currentUser = this.getCurrentUser();
        User ideaOwner = idea.getOwner();
        User projectOwner = idea.getProject().getOwner();
        if(!currentUser.equals(ideaOwner) && !currentUser.equals(projectOwner)) {
            //if not admin and not owner of the idea
            if(!(currentUser instanceof Administrator)) {
                return new ErrorResolution(403, "You can't remove this idea ");
            }
        }
        
        // Remove the idea
        ideaFacade.drop(idea); 
        
        // Return to project's page
        RedirectResolution res = new RedirectResolution(fr.ensimag.ack.eggsale.web.action.project.ShowActionBean.class);
        res.addParameter("id",idea.getProject().getId());
        return res;
    }
}
