package fr.ensimag.ack.eggsale.db.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Message extends BaseEntity {
    
    @Lob
    @Column(name = "content", nullable = false)
    protected String content; 
    
    @ManyToOne()
    @JoinColumn(name = "userId", nullable = false)
    protected User owner;   
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
}
