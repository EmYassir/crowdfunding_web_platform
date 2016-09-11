package fr.ensimag.ack.eggsale.web.action.attachment;

import fr.ensimag.ack.eggsale.db.entity.Attachment;
import fr.ensimag.ack.eggsale.db.facade.AttachmentFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

public class DownloadActionBean extends BaseActionBean {

    @EJB
    AttachmentFacade attachmentFacade;
    private Long attachmentId;
    private Attachment attachment;

    public Long getAttachmentId() {
	return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
	this.attachmentId = attachmentId;
    }

    public Attachment getAttachment() {
	return attachment;
    }

    @DefaultHandler
    public Resolution show() {

	// Ensure the attachment exists
	if (attachmentId != null) {
	    attachment = attachmentFacade.findById(attachmentId);
	}
	if (attachment == null) {
	    return new ErrorResolution(404, "The attachment does not exists");
	}

	return new StreamingResolution("") {
	    @Override
	    public void stream(HttpServletResponse response) throws Exception {
		response.getOutputStream().write(attachment.getContent());
	    }
	}.setFilename(attachment.getFileName());

    }
}
