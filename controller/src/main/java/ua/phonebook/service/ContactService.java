package ua.phonebook.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phonebook.dao.ContactDao;
import ua.phonebook.exceptions.EditException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.Contact;
import ua.phonebook.model.EditModel;

/**
 * Created by dexter on 21.04.16.
 */
@Service
public class ContactService {

    private static final Logger LOGGER = Logger.getLogger(ContactService.class);

    @Autowired
    private ContactDao contactDao;


    public Contact editContact(EditModel target, String targetId) throws EditException {

        try{
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

        } catch (Exception e){
            LOGGER.error("Exception during edit Contact : ", e);
            throw new EditException("Login or email has already used");
        }

        LOGGER.debug("***Contact profile has already changed");

        return contactDao.findContactById(Integer.parseInt(targetId));
    }

    public void saveContact(Contact contact) throws RegistrationException{
        try{
            contactDao.save(contact);
        }catch (Exception e){
            LOGGER.error("***Exception during persistence Contact : ", e);
            throw new RegistrationException("Login or email has already used");
        }
        LOGGER.info("***New contact has been saved");
    }

    public Contact remove(String targetId){
        Contact contact = contactDao.findContactById(Integer.parseInt(targetId));
        LOGGER.debug("This Contact will be permanently removed : " + contact);
        contactDao.remove(Integer.parseInt(targetId));
        return contact;
    }

    public Contact remove(int targetId){
        Contact contact = contactDao.findContactById(targetId);
        LOGGER.debug("This Contact will be permanently removed : " + contact);
        contactDao.remove(targetId);
        return contact;
    }
}
