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


    public Contact changeContact(EditModel target, String targetId) {

        contactDao.changeFirstName(target.getId(), target.getFirstName());
        LOGGER.debug("***First Name was changed");
        contactDao.changeLastName(target.getId(), target.getLastName());
        LOGGER.debug("***Last Name was changed");
        contactDao.changeEmail(target.getId(), target.getEmail());
        LOGGER.debug("***Email was changed");
        contactDao.changeMobileTelephone(target.getId(), target.getMobilePhone());
        LOGGER.debug("***Mobile Phone was changed");
        contactDao.changeHomeTelephone(target.getId(), target.getHomePhone());
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
}
