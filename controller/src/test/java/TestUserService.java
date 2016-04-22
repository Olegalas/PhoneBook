import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.service.UserService;
import ua.phonebook.model.User;

/**
 * Created by dexter on 17.04.16.
 */
@Ignore
public class TestUserService {

    private static final Logger LOGGER = Logger.getLogger(TestUserService.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
    private UserService service = context.getBean(UserService.class);
    private static User user1;
    private static User user2;

    static {

        user1 = new User();
        user1.setLogin("login");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("email@ukr.net");
        user1.setMobilePhone("1111");
        user1.setHomePhone("2222");
        user1.setPass("pass");

        user2 = new User();
        user2.setLogin("login2");
        user2.setFirstName("firstName2");
        user2.setLastName("lastName2");
        user2.setEmail("email2@ukr.net");
        user2.setMobilePhone("11111");
        user2.setHomePhone("22222");
        user2.setPass("pass");

    }

    @After
    public void after() throws ClassNotFoundException {
        DropTables.drop();
    }

    @Before
    public void before(){}

    @Test
    public void saveAndFindUser() throws RegistrationException, LoginException {

        service.registration(user1);
        User userFromDB = service.login(user1.getLogin(), user1.getPass());

        Assert.assertEquals(user1, userFromDB);
    }

    @Test
    public void changeUser() throws RegistrationException, LoginException {

        service.registration(user1);

        String newFirstName = "newFirstName";
        String newLastName = "newLastName";
        String newEmail = "newEmail@gmail.com";
        String newMobilePhone = "newMobilePhone";
        String newHomePhone = "newHomePhone";
        String newPass = "newPass";

        service.changeFirstName(user1.getId(), newFirstName);
        service.changeLastName(user1.getId(), newLastName);
        service.changeEmail(user1.getId(), newEmail);
        service.changeMobilePhone(user1.getId(), newMobilePhone);
        service.changeHomePhone(user1.getId(), newHomePhone);
        service.changePass(user1.getId(), newPass);

        User actualUser = service.login(user1.getLogin(), newPass);

        Assert.assertEquals(newFirstName, actualUser.getFirstName());
        Assert.assertEquals(newLastName, actualUser.getLastName());
        Assert.assertEquals(newEmail, actualUser.getEmail());
        Assert.assertEquals(newMobilePhone, actualUser.getMobilePhone());
        Assert.assertEquals(newHomePhone, actualUser.getHomePhone());
        Assert.assertEquals(newPass, actualUser.getPass());
    }

    @Test
    public void removeUser() throws RegistrationException {

        service.registration(user2);

        User removedUser = service.removeUser(user2.getId());
        Assert.assertEquals(user2, removedUser);

        User nullUser = service.findUser(user2.getId());
        Assert.assertNull(nullUser);

    }
}
