package fr.ensimag.ack.eggsale.db.facade;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.entity.Project;
import fr.ensimag.ack.eggsale.db.entity.Project.SortOrder;
import fr.ensimag.ack.eggsale.db.entity.User;
import java.util.List;

public interface ProjectFacade extends BaseFacade<Project> {
    
    public Project create(String name, float price, String description, boolean salable, User currentUser, List<Attachment> newAttachments);
    
    public List<Project> paginate(int projectsPerPage, int currentPage, SortOrder sortOrder, boolean sortDescending, String searchQuery);

    public void update(Long id, String name, float price, String description, boolean salable, List<Attachment> newAttachments);
}