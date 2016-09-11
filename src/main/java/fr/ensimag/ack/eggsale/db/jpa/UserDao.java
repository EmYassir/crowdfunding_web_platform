package fr.ensimag.ack.eggsale.db.jpa;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Singleton(name = "UserFacade")
public class UserDao extends AbstractDao<User> implements UserFacade {
    
    public UserDao() {
	    super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User authenticate(String email, String password) {
	TypedQuery<User> q;
	EntityManager em = getEntityManager();

	q = (TypedQuery<User>) em.createQuery("from User where email = :email and password = :password");
	q.setParameter("email", email);
	q.setParameter("password", password);

	List<User> list = q.getResultList();
	return (list.isEmpty()) ? null : list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByEmail(String email) {
	TypedQuery<User> q;
	EntityManager em = getEntityManager();

	q = (TypedQuery<User>) em.createQuery("from User where email = :email");
	q.setParameter("email", email);

	List<User> list = q.getResultList();
	return (list.isEmpty()) ? null : list.get(0);
    }        

    public void updateUserCarts(Long userID, Cart cart) {
	User user = findById(userID);
	user.addCart(cart);
	this.merge(user);
    }
    
    
    public User create(String email, String password, String username, String description) {	
	User user = new User();
	user.set(email, username, description);
	user.setPassword(password);
	persist(user);
	return user;
    }
    
    public void update(User user, String email, String username, String description) {	
	user.set(email, username, description);
	System.out.println("User mis à jour");
    }
}
