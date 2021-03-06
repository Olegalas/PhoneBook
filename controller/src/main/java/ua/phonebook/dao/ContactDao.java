package ua.phonebook.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.phonebook.model.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dexter on 20.04.16.
 */
@Component
@Transactional
public class ContactDao {

    private final static Logger LOGGER = Logger.getLogger(ContactDao.class);

    @PersistenceContext
    private EntityManager manager;

    public ContactDao() {
    }

    public void save(Contact contact) {
        manager.persist(contact);
    }

    public Contact findContactById(int idContact) {
        return manager.find(Contact.class, idContact);
    }

    public Contact findContactByLogin(String login) {
        return manager.createQuery("SELECT u FROM Contact u WHERE u.login = :login", Contact.class).
                setParameter("login", login).getSingleResult();

    }

    public void changeLogin(int idContact, String newLogin) {
        manager.createQuery("UPDATE Contact set login = :login WHERE id = :id").
                setParameter("login", newLogin).setParameter("id", idContact).executeUpdate();
    }

    public void changeFirstName(int idContact, String newFirstName) {
        manager.createQuery("UPDATE Contact set firstName = :firstName WHERE id = :id").
                setParameter("firstName", newFirstName).setParameter("id", idContact).executeUpdate();
    }

    public void changeLastName(int idContact, String newLastName) {
        manager.createQuery("UPDATE Contact set lastName = :lastName WHERE id = :id").
                setParameter("lastName", newLastName).setParameter("id", idContact).executeUpdate();
    }

    public void changeEmail(int idContact, String newEmail) {
        manager.createQuery("UPDATE Contact set email = :email WHERE id = :id").
                setParameter("email", newEmail).setParameter("id", idContact).executeUpdate();
    }

    public void changeMobileTelephone(int idContact, String number) {
        manager.createQuery("UPDATE Contact set mobilePhone = :mobileTelephone WHERE id = :id").
                setParameter("mobileTelephone", number).setParameter("id", idContact).executeUpdate();
    }

    public void changeHomeTelephone(int idContact, String number) {
        manager.createQuery("UPDATE Contact set homePhone = :homeTelephone WHERE id = :id").
                setParameter("homeTelephone", number).setParameter("id", idContact).executeUpdate();
    }

    public void changePass(int idContact, String pass) {
        manager.createQuery("UPDATE Contact set pass = :pass WHERE id = :id").
                setParameter("pass", pass).setParameter("id", idContact).executeUpdate();
    }

    public void remove(int idContact) {
        manager.createQuery("DELETE FROM Contact WHERE id = :id").setParameter("id", idContact).executeUpdate();
    }
}
