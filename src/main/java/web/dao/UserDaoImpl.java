package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        manager.merge(user);
    }

    @Override
    public List<User> allUsers() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void deleteUserById(Long id) {
        manager.remove(getUserById(id));
    }

    @Override
    public User getUserById(Long id) {
        return manager.find(User.class, id);
    }
}
