package fr.ensimag.ack.eggsale.db.jpa;

import javax.ejb.Singleton;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.facade.AttachmentFacade;

@Singleton(name = "AttachmentFacade")
public class AttachmentDao extends AbstractDao<Attachment> implements AttachmentFacade {

	public AttachmentDao() {
		super(Attachment.class);
		// TODO Auto-generated constructor stub
	}
        
        @Override
        public Attachment create(String fileName, byte[] content) {
                Attachment attachment = new Attachment();
                attachment.setFileName(fileName);
                attachment.setContent(content);
                this.persist(attachment);
                return attachment;
        }
}
