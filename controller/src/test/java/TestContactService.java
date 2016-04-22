import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class TestContactService {

    private static final Logger LOGGER = Logger.getLogger(TestContactService.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

    private ContactService contactService = context.getBean(ContactService.class);
    private UserService userService = context.getBean(UserService.class);

    private static User user;

    private static Contact contact1;
    private static EditModel model;

    static {

        user = new User();
        user.setLogin("login");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@ukr.net");
        user.setMobilePhone("1111");
        user.setHomePhone("2222");
        user.setPass("pass");

        contact1 = new Contact();
        contact1.setLogin("contact");
        contact1.setFirstName("firstName");
        contact1.setLastName("lastName");
        contact1.setEmail("contactl@ukr.net");
        contact1.setMobilePhone("1111");
        contact1.setHomePhone("2222");

        model = new EditModel();
        model.setLogin("contact2");
        model.setFirstName("firstName");
        model.setLastName("lastName");
        model.setEmail("contact2@ukr.net");
        model.setMobilePhone("1111");
        model.setHomePhone("2222");

    }

    @After
    public void after() throws ClassNotFoundException {
        DropTables.drop();
    }

    @Before
    public void before(){}

    @Test
    public void registrationAndFind() throws RegistrationException {

        userService.registration(user);
        LOGGER.debug("***User was saved");

        user.getPhoneBook().add(contact1);
        Contact newContact = new Contact(contact1, user);

        contactService.saveContact(newContact);
        LOGGER.debug("***Contact was saved");


        User fromDB = userService.findUser(user.getId());

        Assert.assertEquals(fromDB.getPhoneBook().get(0), newContact);

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
