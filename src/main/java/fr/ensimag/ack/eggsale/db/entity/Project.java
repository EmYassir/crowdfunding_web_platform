package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import net.sourceforge.stripes.util.Base64;

@Entity
public class Project extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum SortOrder {
        CREATION_DATE
    }
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "price", nullable = false)
    private float price;
    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "isSalable", nullable = false)
    private boolean isSalable;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private User owner;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    private List<Idea> ideas;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallDescription(){
        int i = 0;
        int maxChar = 220;
        while( i < description.length() &&  i<maxChar ){
            if(description.charAt(i)=='\n' || description.charAt(i) == '\t'){                
                return description.substring(0,i-1)+"...";
            }
            i++;
        }
        if(description.length()>=maxChar){
            return description.substring(0,maxChar-1)+"...";
        }
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isIsSalable() {
        return isSalable;
    }

    public void setIsSalable(boolean isSalable) {
        this.isSalable = isSalable;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> product) {
        this.attachments = product;
    }
    
    public String getFirstImage() {
        if(getAttachments().isEmpty())
            return "";
        return Base64.encodeBytes(getAttachments().get(0).getContent());
    }

    public void addIdea(Idea idea) {
        this.ideas.add(idea);
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeIdea(Idea idea) {
        this.ideas.remove(idea);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }
    
    public int getActivityCounter() {
    	int counter = 0;
    	for (Idea idea : this.ideas) {
    		counter += idea.comments.size() + 1;
    	}
    	return counter;
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Project[ id=" + getId() + " ]";
    }
}
