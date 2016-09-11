package fr.ensimag.ack.eggsale.db.jpa;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.Project.SortOrder;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.ProjectFacade;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Singleton(name = "ProjectFacade")
public class ProjectDao extends AbstractDao<Project> implements ProjectFacade {

    public ProjectDao() {
        super(Project.class);
        // TODO Auto-generated constructor stub
    }    

    @Override
    public Project create(String name, float price, String description, boolean salable, User user, List<Attachment> newAttachments) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setIsSalable(salable);
        project.setPrice(price);
        project.setOwner(user);
        this.persist(project);
        if(newAttachments !=null && !newAttachments.isEmpty()){
            for( Attachment att : newAttachments){
                att.setProject(project);
            }                        
        }
       
        return project;
    }

    public List<Project> paginate(int projectsPerPage, int currentPage, SortOrder sortOrder, boolean sortDescending, String searchQuery) {
        
        //return this.findAll();
	TypedQuery<Project> q;
	EntityManager em = getEntityManager();
        String query = "from Project";
        if (!searchQuery.equals("")){
            query +=" where name like :name";
        }                
	q = (TypedQuery<Project>) em.createQuery(query);
        if (!searchQuery.equals("")){
            q.setParameter("name", "%"+searchQuery+"%");
        }
        if (!searchQuery.equals("")){
            query +=" where name like ";
        }       
        q.setMaxResults(projectsPerPage);
        q.setFirstResult(currentPage*projectsPerPage); 
	List<Project> list = q.getResultList();
	return list;
    }

    public void update(Long id, String name, float price, String description, boolean salable, List<Attachment> newAttachments) {
        Project project = findById(id);
        project.setName(name);
        project.setDescription(description);
        project.setIsSalable(salable);
        project.setPrice(price);
        project.setAttachments(newAttachments);
        this.merge(project);
    }    
}
