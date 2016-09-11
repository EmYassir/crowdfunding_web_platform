package fr.ensimag.ack.eggsale.db.facade;
import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.User;

public interface UserFacade extends BaseFacade<User> {
	public User authenticate(String email, String password);
	public User findByEmail(String email);
	public User create(String email, String password, String username, String description);
	public void update(User user, String email, String username, String description);	
        public void updateUserCarts(Long userID, Cart cart);
}
