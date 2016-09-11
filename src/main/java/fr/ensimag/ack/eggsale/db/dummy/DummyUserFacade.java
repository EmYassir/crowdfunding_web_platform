package fr.ensimag.ack.eggsale.db.dummy;

import fr.ensimag.ack.eggsale.db.entity.Cart;
import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import javax.annotation.PostConstruct;

public class DummyUserFacade extends DummyFacade<User> implements UserFacade {

    //DummyProjectFacade projectFacade;
    @PostConstruct
    public void createInitialUsers() {
        User user = new User();
        user.setUserName("yassir");
        user.setPassword("yassir");
        user.setEmail("elmesbay@ensimag.imag.fr");
        persist(user);
        user = new User();
        user.setUserName("manou");
        user.setPassword("manou");
        user.setEmail("boustela@ensimag.imag.fr");
        persist(user);
        user = new User();
        user.setUserName("antoine");
        user.setPassword("antoine");
        user.setEmail("antoine.duparay@ensimag.fr");
        persist(user);
        user = new User();
        user.setUserName("alex");
        user.setPassword("alex");
        user.setEmail("outroalex@gmail.com");
        persist(user);
        user = new User();
        user.setUserName("mohammed");
        user.setPassword("mohammed");
        user.setEmail("bennism@ensimag.fr");
        persist(user);
        user = new User();
        user.setUserName("nikita");
        user.setPassword("nikita");
        user.setEmail("nikita.kwiatkowski@ensimag.fr");
        persist(user);
        user = new User();
        user.setUserName("pierre");
        user.setPassword("pierre");
        user.setEmail("proudhpi@ensimag.imag.fr");
        persist(user);
        user = new User();
        user.setUserName("stephane");
        user.setPassword("stephane");
        user.setEmail("stephane.jeannin@ensimag.imag.fr");
        persist(user);
    }

    @Override
    public User authenticate(String email, String password) {
        for (User user : objs.values()) {
            if ((user.getEmail().equals(email))) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {

        for (User user : objs.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }
    
    public User create(String email, String password, String username, String description) {
	User user = new User();
	user.set(email, username, description);
	user.setPassword(password);
	return user;
    }

    public void update(User user, String email, String username, String description) {
	user.set(email, username, description);
    }

    public void updateUserCarts(Long userID, Cart cart) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
