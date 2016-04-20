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

    public int saveContact(Contact contact){
        try {
            manager.persist(contact);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            return -1;
        }
        return contact.getId();
    }

    public Contact findContactById(int idContact){
        return manager.find(Contact.class, idContact);
    }

    public Contact findContactByLogin(String login){
        return manager.createQuery("SELECT u FROM Contact u WHERE u.login = :login", Contact.class).
                setParameter("login", login).getSingleResult();

    }

    public void changeFirstName(int idContact, String newFirstName){
        manager.createQuery("UPDATE Contact set firstName = :firstName WHERE id = :id").
                setParameter("firstName", newFirstName).setParameter("id", idContact).executeUpdate();
    }

    public void changeLastName(int idContact, String newLastName){
        manager.createQuery("UPDATE Contact set lastName = :lastName WHERE id = :id").
                setParameter("lastName", newLastName).setParameter("id", idContact).executeUpdate();
    }

    public void changeEmail(int idContact, String newEmail){
        manager.createQuery("UPDATE Contact set email = :email WHERE id = :id").
                setParameter("email", newEmail).setParameter("id", idContact).executeUpdate();
    }

    public void changeMobileTelephone(int idContact, String number){
        manager.createQuery("UPDATE Contact set mobileTelephone = :mobileTelephone WHERE id = :id").
                setParameter("mobileTelephone", number).setParameter("id", idContact).executeUpdate();
    }

    public void changeHomeTelephone(int idContact, String number){
        manager.createQuery("UPDATE Contact set homeTelephone = :homeTelephone WHERE id = :id").
                setParameter("homeTelephone", number).setParameter("id", idContact).executeUpdate();
    }

    public void changePass(int idContact, String pass){
        manager.createQuery("UPDATE Contact set pass = :pass WHERE id = :id").
                setParameter("pass", pass).setParameter("id", idContact).executeUpdate();
    }

    public Contact remove(int idContact){
        Contact contact = manager.find(Contact.class, idContact);
        manager.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", idContact).executeUpdate();
        return contact;
    }
}
