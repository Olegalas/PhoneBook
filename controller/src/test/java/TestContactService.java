import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.phonebook.exceptions.EditException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.Contact;
import ua.phonebook.model.EditModel;
import ua.phonebook.model.User;
import ua.phonebook.service.ContactService;
import ua.phonebook.service.UserService;

/**
 * Created by dexter on 22.04.16.
 */
@Ignore
public class TestContactService {

    private static final Logger LOGGER = Logger.getLogger(TestContactService.class);

    private static String MESSAGE_REGISTRATION_EXCEPTION = "Login or email has already used";
    private static String MESSAGE_LOGIN_EXCEPTION = "Login or pass is not correct";
    private static String MESSAGE_EDIT_EXCEPTION = "Login or email has already used";

    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

    private ContactService contactService = context.getBean(ContactService.class);
    private UserService userService = context.getBean(UserService.class);

    private static User user;

    private Contact contact1;
    private EditModel model;


    @After
    public void after() throws ClassNotFoundException {
        DropTables.drop();
    }

    @Before
    public void before(){

        user = new User();
        user.setLogin("login");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@ukr.net");
        user.setMobilePhone("+38(044)444-44-44");
        user.setHomePhone("+38(044)444-44-44");
        user.setPass("pass");

        contact1 = new Contact();
        contact1.setLogin("contact");
        contact1.setFirstName("firstName");
        contact1.setLastName("lastName");
        contact1.setEmail("contactl@ukr.net");
        contact1.setMobilePhone("+38(044)555-55-55");
        contact1.setHomePhone("+38(044)555-55-5");

        model = new EditModel();
        model.setLogin("contact2");
        model.setFirstName("firstName");
        model.setLastName("lastName");
        model.setEmail("contact2@ukr.net");
        model.setMobilePhone("+38(044)666-66-66");
        model.setHomePhone("+38(044)666-66-66");

    }

    @Test
    public void createAndFind() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        contactService.saveContact(newContact);
        LOGGER.debug("***Contact was saved");


        User fromDB = userService.findUser(user.getId());

        Assert.assertEquals(fromDB.getPhoneBook().get(0), newContact);

    }

    @Test // wrong phone format
    public void createNegativeTelephoneFormat() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        contact1.setMobilePhone("380934564565");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        try{
            contactService.saveContact(newContact);
        }catch (RegistrationException e){
            Assert.assertEquals(MESSAGE_REGISTRATION_EXCEPTION, e.getMessage());
        }

    }

    @Test // wrong email
    public void createNegativeWrongEmail() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        contact1.setEmail("email");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        try{
            contactService.saveContact(newContact);
        }catch (RegistrationException e){
            Assert.assertEquals(MESSAGE_REGISTRATION_EXCEPTION, e.getMessage());
        }

    }

    @Test
    public void changeContact() throws RegistrationException, EditException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        contactService.saveContact(newContact);
        LOGGER.debug("***Contact was saved");

        contactService.editContact(model, String.valueOf(newContact.getId()));

        User fromDB = userService.findUser(user.getId());

        Assert.assertEquals(fromDB.getPhoneBook().get(0).getLogin(), model.getLogin());

    }

    @Test // too short last name
    public void changeContactNegative() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        contactService.saveContact(newContact);
        LOGGER.debug("***Contact was saved");

        try {
            model.setLastName("Dan");
            contactService.editContact(model, String.valueOf(newContact.getId()));
        } catch (EditException e) {
            Assert.assertEquals(MESSAGE_EDIT_EXCEPTION, e.getMessage());
        }

    }

    @Test
    public void removeContact() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        contactService.saveContact(newContact);
        LOGGER.debug("***Contact was saved");

        contactService.remove(newContact.getId());

        User fromDB = userService.findUser(user.getId());

        Assert.assertEquals(0, fromDB.getPhoneBook().size());
    }
}
