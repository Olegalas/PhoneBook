import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.phonebook.controller.exceptions.RegistrationException;
import ua.phonebook.controller.service.UserService;
import ua.phonebook.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dexter on 17.04.16.
 */
public class TestDao {

    private static final Logger LOGGER = Logger.getLogger(TestDao.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");


    @After
    public void after() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "root")) {

            Statement statement = connection.createStatement();
            statement.execute("drop table hibernate_sequence");
            statement.execute("drop table users_users");
            statement.execute("drop table users");
            LOGGER.info("********DataBase was removed");

        } catch (SQLException e) {
            LOGGER.error("Error connection");
        }
    }

    @Test
    public void saveAndFindUser() throws RegistrationException {

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
}
