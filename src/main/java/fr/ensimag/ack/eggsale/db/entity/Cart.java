package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Cart extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "total", nullable = false)
    private float total;
    
    @Column(name = "isValidate", nullable = false)
    private boolean isValidate;
    
    @ManyToOne()
    @JoinColumn(name = "userId", nullable = false)
    private User owner;
    
    @ManyToMany()
    @JoinTable(name = "CartProject")
    private List<Project> projects;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isIsValidate() {
        return isValidate;
    }

    public void setIsValidate(boolean isValidate) {
        this.isValidate = isValidate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Cart[ id=" + getId() + " ]";
    }

    public void addProject(Project project) {
        this.total += project.getPrice();
        this.projects.add(project);
    }

    public void removeProject(Project project) {
        this.total -= project.getPrice();
        this.projects.remove(project);
    }


 
    public Cart() {
        projects = new ArrayList<Project>();
    }
}
