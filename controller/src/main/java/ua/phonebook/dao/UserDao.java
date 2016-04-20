package ua.phonebook.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.phonebook.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dexter on 15.04.16.
 */
@Component
@Transactional
public class UserDao {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager manager;

    public UserDao() {
    }

    public int saveUser(User user){
        try {
            manager.persist(user);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            return -1;
        }
        return user.getId();
    }

    public User findUserById(int idUser){
        return manager.find(User.class, idUser);
    }

    public User findUserByLogin(String login){
        return manager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class).
                setParameter("login", login).getSingleResult();
    }

    public void changeFirstName(int idUser, String newFirstName){
        manager.createQuery("UPDATE User set firstName = :firstName WHERE id = :id").
                setParameter("firstName", newFirstName).setParameter("id", idUser).executeUpdate();
    }

    public void changeLastName(int idUser, String newLastName){
        manager.createQuery("UPDATE User set lastName = :lastName WHERE id = :id").
                setParameter("lastName", newLastName).setParameter("id", idUser).executeUpdate();
    }

    public void changeEmail(int idUser, String newEmail){
        manager.createQuery("UPDATE User set email = :email WHERE id = :id").
                setParameter("email", newEmail).setParameter("id", idUser).executeUpdate();
    }

    public void changeMobileTelephone(int idUser, String number){
        manager.createQuery("UPDATE User set mobileTelephone = :mobileTelephone WHERE id = :id").
                setParameter("mobileTelephone", number).setParameter("id", idUser).executeUpdate();
    }

    public void changeHomeTelephone(int idUser, String number){
        manager.createQuery("UPDATE User set homeTelephone = :homeTelephone WHERE id = :id").
                setParameter("homeTelephone", number).setParameter("id", idUser).executeUpdate();
    }

    public void changePass(int idUser, String pass){
        manager.createQuery("UPDATE User set pass = :pass WHERE id = :id").
                setParameter("pass", pass).setParameter("id", idUser).executeUpdate();
    }

    public User remove(int idUser){
        User user = manager.find(User.class, idUser);
        manager.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", idUser).executeUpdate();
        return user;
    }
}
