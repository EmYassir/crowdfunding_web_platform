package fr.ensimag.ack.eggsale.db.jpa;

import javax.ejb.Singleton;

import fr.ensimag.ack.eggsale.db.entity.Comment;
import fr.ensimag.ack.eggsale.db.facade.CommentFacade;

@Singleton(name = "CommentFacade")
public class CommentDao extends AbstractDao<Comment> implements CommentFacade {

	public CommentDao() {
		super(Comment.class);
		// TODO Auto-generated constructor stub
	}

}
