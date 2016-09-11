package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attachment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int maxLength = 1000000; // slightly less than 1 MB
    
    @Column(name = "fileName", nullable = false)
    private String fileName;
    
    @Column(name="content", nullable=false, length=maxLength)
    private byte[] content;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="ideaId", nullable = true)
    private Idea idea;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="commentId", nullable = true)
    private Comment comment;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="projectId", nullable = true)
    private Project project;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
    
    public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Attachment[ id=" + getId() + " ]";
    }
    
}
