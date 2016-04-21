package ua.phonebook.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phonebook.dao.ContactDao;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.model.Contact;
import ua.phonebook.model.EditModel;
import ua.phonebook.model.User;

/**
 * Created by dexter on 21.04.16.
 */
@Service
public class ContactService {

    private static final Logger LOGGER = Logger.getLogger(ContactService.class);

    @Autowired
    private ContactDao contactDao;


    public Contact editContact(EditModel target, String targetId) {

        contactDao.changeLogin(Integer.parseInt(targetId), target.getLogin());
        LOGGER.debug("***Login was changed");
        contactDao.changeFirstName(Integer.parseInt(targetId), target.getFirstName());
        LOGGER.debug("***First Name was changed");
        contactDao.changeLastName(Integer.parseInt(targetId), target.getLastName());
        LOGGER.debug("***Last Name was changed");
        contactDao.changeEmail(Integer.parseInt(targetId), target.getEmail());
        LOGGER.debug("***Email was changed");
        contactDao.changeMobileTelephone(Integer.parseInt(targetId), target.getMobilePhone());
        LOGGER.debug("***Mobile Phone was changed");
        contactDao.changeHomeTelephone(Integer.parseInt(targetId), target.getHomePhone());
        LOGGER.debug("***Home Phone was changed");

        LOGGER.debug("***Profile has already changed");

        return contactDao.findContactById(Integer.parseInt(targetId));
    }

    public void saveContact(Contact contact) throws LoginException {
        int idContact = contactDao.save(contact);
        if(idContact == -1){
            throw new LoginException("Login already has used");
        }
        LOGGER.info("***New contact has been saved");
    }

    public Contact remove(String tagetId){
        Contact contact = contactDao.findContactById(Integer.parseInt(tagetId));
        contactDao.remove(Integer.parseInt(tagetId));
        return contact;
    }
}
