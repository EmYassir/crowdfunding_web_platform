/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.project;

import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class DeleteActionBean extends BaseActionBean{
    @Validate(required=true)
    public Long id;
    
    @EJB
    ProjectFacade projectFacade;
   
    public Resolution delete() {
        Resolution auth = this.ensureLoggedIn();
        if (auth != null) {return auth;}
        Project project = projectFacade.findById(id);
        projectFacade.drop(project);
        return new RedirectResolution(ListActionBean.class);
    }
}
