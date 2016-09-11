package fr.ensimag.ack.eggsale.db.entity;

import fr.ensimag.ack.eggsale.db.MD5Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User",
    uniqueConstraints = {
    @UniqueConstraint(columnNames = { "email" })
})
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "username", nullable = false)
    private String userName;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Lob
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Project> projects;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Idea> ideas;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @Transient
    private Cart currentOrder;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Cart> commands;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5Util.md5Hex(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getHashedEmail() {
	return MD5Util.md5Hex(getEmail());
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

    public List<Cart> getCommands() {
        return commands;
    }
    
    public Cart getCurrentOrder() {
        return currentOrder;
    }
    
    public void setCurrentOrder(Cart currentOrder) {
        this.commands.add(currentOrder);
        this.currentOrder = currentOrder;
    }    
    
    public void set(String email, String username, String description) {	
	setEmail(email);
	//If the username is empty, get the first part of the email as the username
	setUserName(
	    (username == null || username.equals("")) ?
	    email.split("@")[0].replace('.', ' ') :
	    username);
	setDescription(description);
    }
    
    public void addProject(Project project) {
        this.projects.add(project);    
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addCart(Cart cart) {
        this.commands.add(cart);
    }

    public void addIdea(Idea idea) {
        this.ideas.add(idea);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public void removeCart(Cart cart) {
        this.commands.remove(cart);
    }

    public void removeIdea(Idea idea) {
        this.ideas.remove(idea);
    }
    
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User)object;
        return user.email.equals(this.email);
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.User[ id=" + getId() + " ]";
    }
}
