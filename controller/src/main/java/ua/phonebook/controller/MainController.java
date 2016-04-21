package ua.phonebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.*;
import ua.phonebook.service.ContactService;
import ua.phonebook.service.UserService;

@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstPage(Model model) {

        LOGGER.debug("***Enter in firstPage method");
        model.addAttribute("personAttribute", new Login());
        model.addAttribute("message", "Enter your login and pass");
        return "loginpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("personAttribute") Login login, Model model) {

        LOGGER.debug("***Enter in loginPost method");

        try {
            LOGGER.debug("***Query on enter.. " + login);
            User user = userService.login(login.getLogin(), login.getPass());
            LOGGER.info("User passed .. user login - " + user.getLogin());
            model.addAttribute("user", user);
            return "homepage";
        } catch (LoginException e) {
            LOGGER.error("***LoginException : ", e);
            model.addAttribute("message", e.getMessage());
            model.addAttribute("personAttribute", new Login());
            model.addAttribute("message", e.getMessage());
            return "loginpage";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        LOGGER.debug("***Enter in login method");

        model.addAttribute("personAttribute", new Login());
        model.addAttribute("message", "Enter your login and pass");
        return "loginpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(Model model) {

        LOGGER.debug("***Enter in registrationGet method");
        model.addAttribute("message", "Sign up");
        model.addAttribute("personAttribute", new EditModel());

        return "registrationpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("personAttribute") EditModel form, Model model) {

        LOGGER.debug("***Enter in registrationPost method");

        User user = new User(form);

        try {
            userService.registration(user);
            LOGGER.info("User has been saved");
            model.addAttribute("message", "Enter your login and pass");
            model.addAttribute("personAttribute", new Login());
            return "loginpage";
        } catch (RegistrationException e) {
            LOGGER.error("***LoginException : ", e);
            model.addAttribute("message", e.getMessage());
            model.addAttribute("personAttribute", new EditModel());
            return "registrationpage";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String userHomePagePost(@RequestParam(value = "id", required = true) String id, Model model) {

        LOGGER.debug("***Enter in userHomePagePost method");
        User user = userService.findUser(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "homepage";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(value = "id", required = true) String id,
                           @RequestParam(value = "targetId", required = true) String targetId,Model model) {

        LOGGER.debug("***Enter in editPost method");


        EditModel editModel = new EditModel(targetId);
        model.addAttribute("personAttribute", editModel);
        model.addAttribute("userId", id);
        model.addAttribute("targetId", targetId);

        return "editpage";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public String contactsPost(@RequestParam(value = "id", required = true) String id, Model model) {

        LOGGER.debug("***Enter in contactsPost method");


        model.addAttribute("message", "All your contacts");
        return "contactspage";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUserPost(@RequestParam(value = "id", required = true) String id,
                               @RequestParam(value = "targetId", required = true) String targetId,
                               @ModelAttribute("personAttribute") EditModel editModel, Model model) {

        LOGGER.debug("***Enter in editUserPost method");

        LOGGER.debug("***Received " + editModel);

        if(id.equals(targetId)){
            userService.changeUser(editModel, targetId);
            LOGGER.debug("***User profile was changed");
        } else {
            contactService.changeContact(editModel, targetId);
            LOGGER.debug("***Contact was changed");
        }

        User user = userService.findUser(Integer.parseInt(id));
        model.addAttribute("user", user);

        return "homepage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id", required = true) String id,
                           @RequestParam(value = "targetId", required = true) String targetId,Model model) {

        LOGGER.debug("***Enter in deletePost method");


        userService.removeContact(targetId);

        User user = userService.findUser(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "homepage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@RequestParam(value = "id", required = true) String id, Model model) {

        LOGGER.debug("***Enter in addPost method");


        model.addAttribute("userId", id);
        model.addAttribute("newContact", new Contact());

        return "add";
    }

    @RequestMapping(value = "/addcontact", method = RequestMethod.POST)
    public String addContactPost(@RequestParam(value = "id", required = true) String id,
                                 @ModelAttribute("newContact") Contact contact, Model model){

        LOGGER.debug("***Enter in addContactPost method");

        try{

            User user = userService.findUser(Integer.parseInt(id));
            Contact newContact = new Contact(contact, user);
            user.getPhoneBook().add(newContact);

            LOGGER.debug("***User : " + id);
            LOGGER.debug("***Want to save : " + contact);

            contactService.saveContact(newContact);
            LOGGER.info("***New contact was saved");

        }catch (LoginException e){

            LOGGER.error("***Exception during save contact : ", e);

            model.addAttribute("userId", id);
            model.addAttribute("personAttribute", new Contact());
            return "add";
        }

        User user = userService.findUser(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "homepage";
    }


}
