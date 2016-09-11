/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author PedroPortable
 */
@Entity
public class Reward extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "amount", nullable = false)
    private float amount;
    
    @OneToOne
    @JoinColumn(name = "ideaId")
    private Idea idea; 

    public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public float getAmount() {
        return amount;
    }

    public void setAmount(float ammount) {
        this.amount = ammount;
    }

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Reward[ id=" + getId() + " ]";
    }
    
}
