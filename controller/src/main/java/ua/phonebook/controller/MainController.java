package ua.phonebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.Login;
import ua.phonebook.model.RegistrationForm;
import ua.phonebook.model.User;
import ua.phonebook.model.Contact;
import ua.phonebook.service.UserService;

@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private UserService service;

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
            User user = service.login(login.getLogin(), login.getPass());
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
        model.addAttribute("personAttribute", new RegistrationForm());

        return "registrationpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("personAttribute") RegistrationForm form, Model model) {

        LOGGER.debug("***Enter in registrationPost method");

        User user = new User(form);

        try {
            service.registration(user);
            LOGGER.info("User has been saved");
            model.addAttribute("message", "Enter your login and pass");
            model.addAttribute("personAttribute", new Login());
            return "loginpage";
        } catch (RegistrationException e) {
            LOGGER.error("***LoginException : ", e);
            model.addAttribute("message", e.getMessage());
            model.addAttribute("personAttribute", new RegistrationForm());
            return "registrationpage";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String userHomePagePost(@RequestParam(value = "id", required = true) String id, Model model) {

        LOGGER.debug("***Enter in userHomePagePost method");


        return "homepage";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(value = "id", required = true) String id,
                           @RequestParam(value = "targetId", required = true) String targetId,Model model) {

        LOGGER.debug("***Enter in editPost method");


        Contact contact = new Contact(targetId);
        model.addAttribute("personAttribute", contact);
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
                               @ModelAttribute("personAttribute") Contact contact, Model model) {

        LOGGER.debug("***Enter in editUserPost method");

        LOGGER.debug("***Received " + contact);
        service.changeUser(contact, id);
        LOGGER.debug("***After save " + contact);
        return "homepage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id", required = true) String id,
                           @RequestParam(value = "targetId", required = true) String targetId,Model model) {

        LOGGER.debug("***Enter in deletePost method");


        service.removeContact(targetId);
        return "homepage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContactPost(@RequestParam(value = "id", required = true) String id, Model model) {

        LOGGER.debug("***Enter in addContactPost method");


        // TODO: 20.04.16 create userDTO... save it... and them edit

        model.addAttribute("message", "Fill up next fields");
        return "editpage";
    }
}
