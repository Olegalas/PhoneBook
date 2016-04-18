package ua.phonebook.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phonebook.dao.UserDao;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.exceptions.LoginException;
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
        int idUser = userDao.saveUser(user);

        if(idUser == -1){
            LOGGER.error("Login has already used");
            throw new RegistrationException("Login has already used");
        }

        LOGGER.info("User was persisted");
        return user;
    }

    public User login(String login, String pass) throws LoginException {
        User user = userDao.findUserByLogin(login);
        if(user != null){
            if(user.getPass().equals(pass)){
                return user;
            }
        }
        throw new LoginException("Login or pass is not correct");
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
}