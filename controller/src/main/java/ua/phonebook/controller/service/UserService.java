package ua.phonebook.controller.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phonebook.controller.dao.UserDao;
import ua.phonebook.controller.exceptions.DataBaseException;
import ua.phonebook.model.User;

/**
 * Created by dexter on 16.04.16.
 */
@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User registration(User user) throws DataBaseException{
        int idUser = userDao.saveUser(user);

        if(idUser == -1){
            LOGGER.error("Login has already used");
            throw new DataBaseException("Login has already used");
        }

        LOGGER.info("User was persisted");
        return user;
    }

    public User login(String login, String pass) throws DataBaseException {
        User user = userDao.findUserByLogin(login);
        if(user != null){
            if(user.getPass().equals(pass)){
                return user;
            }
        }
        throw new DataBaseException("Login or pass is not correct");
    }
}
