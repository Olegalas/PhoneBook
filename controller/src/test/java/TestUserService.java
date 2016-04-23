import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.phonebook.exceptions.EditException;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.EditModel;
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

    private static String MESSAGE_REGISTRATION_EXCEPTION = "Login or email has already used";
    private static String MESSAGE_LOGIN_EXCEPTION = "Login or pass is not correct";
    private static String MESSAGE_EDIT_EXCEPTION = "Login or email has already used";

    private static String NEW_FIRST_NAME = "newFirstName";
    private static String NEW_LAST_NAME = "newLastName";
    private static String NEW_EMAIL = "newEmail@gmail.com";
    private static String NEW_MOBILE_PHONE = "newMobilePhone";
    private static String NEW_HOME_PHONE = "newHomePhone";
    private static String NEW_PASS = "newPass";


    private User user1;
    private User user2;
    private User wrongUser1; //with incorrect login
    private User wrongUser2; //with incorrect email

    @After
    public void after() throws ClassNotFoundException {
        DropTables.drop();
    }

    @Before
    public void before(){
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

        wrongUser1 = new User();
        wrongUser1.setLogin("nik");
        wrongUser1.setFirstName("firstName");
        wrongUser1.setLastName("lastName");
        wrongUser1.setEmail("emailukr.net");
        wrongUser1.setMobilePhone("1111");
        wrongUser1.setHomePhone("2222");
        wrongUser1.setPass("pass");

    }

    @Test
    public void saveAndFindUser() throws RegistrationException, LoginException {

        service.registration(user1);
        User userFromDB = service.login(user1.getLogin(), user1.getPass());

        Assert.assertEquals(user1, userFromDB);
    }

    @Test // Duplicate unique fields
    public void saveUserNegative() throws RegistrationException {

        service.registration(user1);
        try {
            service.registration(user1);
        }catch(RegistrationException e){
            Assert.assertEquals(e.getMessage(), MESSAGE_REGISTRATION_EXCEPTION);
        }
    }

    @Test // too short login
    public void saveUserNegativeShortLogin() throws RegistrationException {

        try {
            service.registration(wrongUser1);
        }catch(RegistrationException e){
            Assert.assertEquals(e.getMessage(), MESSAGE_REGISTRATION_EXCEPTION);
        }
    }

    @Test // too wrongEmail
    public void saveUserNegativeLongLogin() throws RegistrationException {

        wrongUser1.setLogin("tooooLoooongLooooogiiiiin");
        try {
            service.registration(wrongUser1);
        }catch(RegistrationException e){
            Assert.assertEquals(e.getMessage(), MESSAGE_REGISTRATION_EXCEPTION);
        }
    }

    @Test // too long login
    public void saveUserNegativeWrongEmail() throws RegistrationException {

        wrongUser1.setLogin("login");
        try {
            service.registration(wrongUser1);
        }catch(RegistrationException e){
            Assert.assertEquals(e.getMessage(), MESSAGE_REGISTRATION_EXCEPTION);
        }
    }

    @Test
    public void loginWithIncorrectPass() throws RegistrationException {

        service.registration(user1);

        try {
            service.login(user1.getLogin(), "wrongPass");
        }catch(LoginException e){
            Assert.assertEquals(e.getMessage(), MESSAGE_LOGIN_EXCEPTION);
        }
    }

    @Test
    public void findNegative() {

        try {
            service.login(user1.getLogin(), user1.getPass());
        } catch (LoginException e) {
            Assert.assertEquals(e.getMessage(), MESSAGE_LOGIN_EXCEPTION);
        }

    }

    @Test
    public void editUser() throws RegistrationException, EditException {
        service.registration(user1);

        EditModel model = new EditModel(String.valueOf(user1.getId()));
        model.setFirstName(NEW_FIRST_NAME);
        model.setLastName(NEW_LAST_NAME);
        model.setEmail(NEW_EMAIL);
        model.setMobilePhone(NEW_MOBILE_PHONE);
        model.setHomePhone(NEW_HOME_PHONE);
        model.setNewPass(NEW_PASS);


        service.editUser(model, String.valueOf(user1.getId()));

        User userFomDB = service.findUser(user1.getId());

        Assert.assertEquals(userFomDB.getFirstName(), NEW_FIRST_NAME);

    }

    @Test
    public void editUserNegative() throws RegistrationException {
        service.registration(user1);

        EditModel model = new EditModel(String.valueOf(user1.getId()));

        model.setFirstName("расейській текст");
        model.setLastName(NEW_LAST_NAME);
        model.setEmail(NEW_EMAIL);
        model.setMobilePhone(NEW_MOBILE_PHONE);
        model.setHomePhone(NEW_HOME_PHONE);
        model.setNewPass(NEW_PASS);

        try {
            service.editUser(model, String.valueOf(user1.getId()));
        } catch (EditException e) {
            Assert.assertEquals(MESSAGE_EDIT_EXCEPTION, e.getMessage());
        }

    }

    @Test
    public void changeUser() throws RegistrationException, LoginException {

        service.registration(user1);

        service.changeFirstName(user1.getId(), NEW_FIRST_NAME);
        service.changeLastName(user1.getId(), NEW_LAST_NAME);
        service.changeEmail(user1.getId(), NEW_EMAIL);
        service.changeMobilePhone(user1.getId(), NEW_MOBILE_PHONE);
        service.changeHomePhone(user1.getId(), NEW_HOME_PHONE);
        service.changePass(user1.getId(), NEW_PASS);

        User actualUser = service.login(user1.getLogin(), NEW_PASS);

        Assert.assertEquals(NEW_FIRST_NAME, actualUser.getFirstName());
        Assert.assertEquals(NEW_LAST_NAME, actualUser.getLastName());
        Assert.assertEquals(NEW_EMAIL, actualUser.getEmail());
        Assert.assertEquals(NEW_MOBILE_PHONE, actualUser.getMobilePhone());
        Assert.assertEquals(NEW_HOME_PHONE, actualUser.getHomePhone());
        Assert.assertEquals(NEW_PASS, actualUser.getPass());
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
