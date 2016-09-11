/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.ack.eggsale.db.facade;

import fr.ensimag.ack.eggsale.db.entity.Attachment;

public interface AttachmentFacade extends BaseFacade<Attachment> {
    public Attachment create(String fileName, byte[] content);
}
