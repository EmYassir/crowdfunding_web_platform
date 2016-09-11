package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class Idea extends Message implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @ManyToOne()
    @JoinColumn(name = "projectId")
    private Project project;
    
    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    
    @OneToOne(mappedBy="idea", cascade = CascadeType.ALL)
    private Reward reward;
    
    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    protected List<Comment> comments;
    
    @ManyToMany
    @JoinTable(name="Idea_VoteUp")
    protected List<User> votedUp;
    
    @ManyToMany
    @JoinTable(name="Idea_VoteDown")
    protected List<User> votedDown;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public List<User> getVotedUp() {
        return votedUp;
    }

    public void setVotedUp(List<User> votedUp) {
        this.votedUp = votedUp;
    }

    public List<User> getVotedDown() {
        return votedDown;
    }

    public void setVotedDown(List<User> votedDown) {
        this.votedDown = votedDown;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Idea[ id=" + getId() + " ]";
    }
}
