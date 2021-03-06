package ua.phonebook.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phonebook.dao.UserDao;
import ua.phonebook.exceptions.EditException;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.EditModel;
import ua.phonebook.model.User;

/**
 * Created by dexter on 16.04.16.
 */
@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User registration(User user) throws RegistrationException {
        try{
            userDao.save(user);
        }catch (Exception e){
            LOGGER.error("***Exception during persistence user : ", e);
            throw new RegistrationException("Login or email has already used");
        }

        LOGGER.info("***User was persisted");
        return user;
    }

    public User login(String login, String pass) throws LoginException {
        try{
            User user = userDao.findUserByLogin(login);
            if(user != null){
                if(user.getPass().equals(pass)){
                    return user;
                }
                LOGGER.warn("***incorrect pass");
            }
        } catch (Exception ignore){/*NOP*/
            LOGGER.error("***Exception ", ignore);
        }
        throw new LoginException("Login or pass is not correct");
    }

    public User findUser(int id) {
        return userDao.findUserById(id);
    }

    public User changeFirstName(int idUser, String newFirstName){
        userDao.changeFirstName(idUser, newFirstName);
        return userDao.findUserById(idUser);
    }

    public User changeLastName(int idUser, String newLastName){
        userDao.changeLastName(idUser, newLastName);
        return userDao.findUserById(idUser);
    }

    public User changeMobilePhone(int idUser, String newMobilePhone){
        userDao.changeMobileTelephone(idUser, newMobilePhone);
        return userDao.findUserById(idUser);
    }

    public User changeHomePhone(int idUser, String newHomePhone){
        userDao.changeHomeTelephone(idUser, newHomePhone);
        return userDao.findUserById(idUser);
    }

    public User changeEmail(int idUser, String newEmail){
        userDao.changeEmail(idUser, newEmail);
        return userDao.findUserById(idUser);
    }

    public User changePass(int idUser, String newPass){
        userDao.changePass(idUser, newPass);
        return userDao.findUserById(idUser);
    }

    public User editUser(EditModel target, String targetId) throws EditException {

        try{
            userDao.changeFirstName(target.getId(), target.getFirstName());
            LOGGER.debug("***First Name was changed");
            userDao.changeLastName(target.getId(), target.getLastName());
            LOGGER.debug("***Last Name was changed");
            userDao.changeEmail(target.getId(), target.getEmail());
            LOGGER.debug("***Email was changed");
            userDao.changeMobileTelephone(target.getId(), target.getMobilePhone());
            LOGGER.debug("***Mobile Phone was changed");
            userDao.changeHomeTelephone(target.getId(), target.getHomePhone());
            LOGGER.debug("***Home Phone was changed");
            changePass(target.getId(), target.getNewPass());
            LOGGER.debug("***Pass was changed");

        }catch (Exception e){
            LOGGER.error("Exception during edit user profile : ", e);
            throw new EditException("Login or email has already used");
        }

        LOGGER.debug("***User profile was changed");
        return userDao.findUserById(Integer.parseInt(targetId));
    }

    public User removeUser(String idContact){
        return userDao.remove(Integer.parseInt(idContact));
    }

    public User removeUser(int idContact){
        return userDao.remove(idContact);
    }
}
