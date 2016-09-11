/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment extends Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne()
    @JoinColumn(name = "ideaId", nullable = false)
    private Idea idea;
    
    @ManyToOne()
    @JoinColumn(name = "parentId", nullable = true)
    private Comment parent;
   
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    protected List<Comment> comments;
    
    @ManyToMany
    @JoinTable(name="Comment_VoteUp")
    protected List<User> votedUp;
    
    @ManyToMany
    @JoinTable(name="Comment_VoteDown")
    protected List<User> votedDown;
    
    
    public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}
        
	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
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

	@Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Comment[ id=" + getId() + " ]";
    }
}
