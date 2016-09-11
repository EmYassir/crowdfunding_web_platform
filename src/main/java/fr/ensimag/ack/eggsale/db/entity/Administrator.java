package fr.ensimag.ack.eggsale.db.entity;

import java.io.Serializable;
import javax.persistence.Entity;


@Entity
public class Administrator extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "fr.ensimag.ack.eggsale.db.entity.Administrator[ id=" + getId() + " ]";
    }
    
}
