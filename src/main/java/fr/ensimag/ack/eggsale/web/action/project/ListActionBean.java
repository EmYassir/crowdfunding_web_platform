/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.web.action.project;

import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.Project.SortOrder;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.Base64;


public class ListActionBean extends BaseActionBean {

    
    public static String VIEW = VIEW_BASE_PATH + "project/list.jsp";

    
    public int projectsPerPage = 10;
    public int currentPage = 0;
    public SortOrder sortOrder = SortOrder.CREATION_DATE;
    public boolean sortDescending = true;
    public String searchQuery ="";
    
    private List<Project> projects;

    private List<String> imageContents = new ArrayList<String>();

    public List<String> getImageContents() {
        return imageContents;
    }

    public void setImageContents(List<String> imageContents) {
        this.imageContents = imageContents;
    }
    
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
     // YE : Ajout pour maintient de la variable "user" dans la session
    //private User currentUser;
    
    public List<Project> getProjects(){
        return projects;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getProjectsPerPage() {
        return projectsPerPage;
    }
    
    // YE : Ajout pour maintient de la variable "user" dans la session
    /*public User getCurrentUser(){
        return currentUser;
    }*/
    
    @EJB
    ProjectFacade projectFacade;

    public Resolution list() {
        //currentUser = this.getCurrentUser();
        projects = projectFacade.paginate(projectsPerPage,
                currentPage, sortOrder, sortDescending, searchQuery);
        return new ForwardResolution(VIEW);
    }
}
