/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.idea;

import fr.ensimag.ack.eggsale.db.entity.Idea;
import fr.ensimag.ack.eggsale.db.facade.IdeaFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ShowActionBean extends BaseActionBean {
    @EJB
    IdeaFacade ideaFacade;
    
    private Long ideaId;
    
    private Idea idea;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Idea getIdea() {
            return idea;
    }  

    @DefaultHandler
    public Resolution show() {
        
        // Ensure the idea exists
        if(ideaId != null) {
            idea = ideaFacade.findById(ideaId);
        }
        if(idea == null) {
            return new ErrorResolution(404,"The idea does not exists");
        }
        
        // Return the idea page
        return new ForwardResolution(VIEW_BASE_PATH + "idea/show.jsp");
    }
}
