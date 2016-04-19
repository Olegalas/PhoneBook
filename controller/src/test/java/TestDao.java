import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.service.UserService;
import ua.phonebook.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dexter on 17.04.16.
 */
@Ignore
public class TestDao {

    private static final Logger LOGGER = Logger.getLogger(TestDao.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");


    @After
    public void after() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "root")) {

            Statement statement = connection.createStatement();
            statement.execute("drop table hibernate_sequence");
            statement.execute("drop table users");
            statement.execute("drop table users_users");
            LOGGER.info("********DataBase was removed");

        } catch (SQLException e) {
            LOGGER.error("Error connection");
        }
    }

    @Test
    public void saveAndFindUser() throws RegistrationException, LoginException {

        UserService service = context.getBean(UserService.class);

        User user = new User();
        user.setLogin("login");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setMobileTelephone("111");
        user.setHomeTelephone("222");
        user.setPass("pass");

        service.registration(user);
        User userFromDB = service.login(user.getLogin(), user.getPass());

        Assert.assertEquals(user, userFromDB);

    }

    @Test
    public void changeUser() throws RegistrationException, LoginException {

        UserService service = context.getBean(UserService.class);

        User user = new User();
        user.setLogin("login");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setMobileTelephone("111");
        user.setHomeTelephone("222");
        user.setPass("pass");

        service.registration(user);

        String newFirstName = "newFirstName";
        String newLastName = "newLastName";
        String newEmail = "newEmail";
        String newMobilePhone = "newMobilePhone";
        String newHomePhone = "newHomePhone";
        String newPass = "newPass";

        service.changeFirstName(user.getId(), newFirstName);
        service.changeLastName(user.getId(), newLastName);
        service.changeEmail(user.getId(), newEmail);
        service.changeMobilePhone(user.getId(), newMobilePhone);
        service.changeHomePhone(user.getId(), newHomePhone);
        service.changePass(user.getId(), newPass);

        User actualUser = service.login(user.getLogin(), newPass);

        Assert.assertEquals(newFirstName, actualUser.getFirstName());
        Assert.assertEquals(newLastName, actualUser.getLastName());
        Assert.assertEquals(newEmail, actualUser.getEmail());
        Assert.assertEquals(newMobilePhone, actualUser.getMobileTelephone());
        Assert.assertEquals(newHomePhone, actualUser.getHomeTelephone());
        Assert.assertEquals(newPass, actualUser.getPass());
    }
}
