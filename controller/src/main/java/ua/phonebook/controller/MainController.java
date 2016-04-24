package ua.phonebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.phonebook.exceptions.EditException;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.Contact;
import ua.phonebook.model.EditModel;
import ua.phonebook.model.Login;
import ua.phonebook.model.User;
import ua.phonebook.service.ContactService;
import ua.phonebook.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstPage(Map<String, Object> model) {

        LOGGER.debug("***Enter in firstPage method");
        model.put("personAttribute", new Login());
        model.put("message", "Enter your login and pass");
        return "loginpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("personAttribute") Login login, Map<String, Object> model) {

        LOGGER.debug("***Enter in loginPost method");

        try {
            LOGGER.debug("***Query on enter.. " + login);
            User user = userService.login(login.getLogin(), login.getPass());
            LOGGER.info("User passed .. user login - " + user.getLogin());
            model.put("user", user);
            return "homepage";
        } catch (LoginException e) {
            LOGGER.error("***LoginException : ", e);
            model.put("message", e.getMessage());
            return "loginpage";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> model) {

        LOGGER.debug("***Enter in login method");

        model.put("personAttribute", new Login());
        model.put("message", "Enter your login and pass");
        return "loginpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(Map<String, Object> model) {

        LOGGER.debug("***Enter in registrationGet method");
        model.put("message", "sign up");
        model.put("personAttribute", new EditModel());
        return "registrationpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@Valid @ModelAttribute("personAttribute") EditModel form,
                                   BindingResult result, Map<String, Object> model) {

        LOGGER.debug("***Enter in registrationPost method");

        if(result.hasErrors()) {
            LOGGER.debug("***Incorrect registration..return to registration page");
            return "registrationpage";
        }

        if(form.getPass().isEmpty() || !form.getPass().equals(form.getRePass())){
            LOGGER.debug("***Different passwords.. return to registration page");
            model.put("passMessage", "Your passwords are different");
            return "registrationpage";
        }


        User user = new User(form);

        try {
            userService.registration(user);
            LOGGER.info("User has been saved");
            model.put("message", "Enter your login and pass");
            model.put("personAttribute", new Login());
            return "loginpage";
        } catch (RegistrationException e) {
            LOGGER.error("***LoginException : ", e);
            model.put("loginMessage", e.getMessage());
            return "registrationpage";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String userHomePagePost(@RequestParam(value = "id", required = true) String id,
                                   Map<String, Object> model) {

        LOGGER.debug("***Enter in userHomePagePost method");
        User user = userService.findUser(Integer.parseInt(id));
        model.put("user", user);

        return "homepage";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(value = "id", required = true) String id,
                           @RequestParam(value = "targetId", required = true) String targetId,
                           Map<String, Object> model) {

        LOGGER.debug("***Enter in editPost method");


        model.put("editModel", new EditModel(targetId));
        model.put("userId", id);
        model.put("targetId", targetId);
        model.put("message", "Edit contact");

        return "editpage";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public String contactsPost(@RequestParam(value = "id") String id,
                               Map<String, Object> model) {

        LOGGER.debug("***Enter in contactsPost method");

        model.put("user", userService.findUser(Integer.parseInt(id)));
        return "contactspage";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUserPost(@RequestParam(value = "id") String id,
                               @RequestParam(value = "targetId") String targetId,
                               @Valid @ModelAttribute("editModel") EditModel editModel,
                               BindingResult result,
                               Map<String, Object> model) {

        LOGGER.debug("***Enter in editUserPost method");

        LOGGER.debug("***Received " + editModel);

        if(result.hasErrors()) {
            LOGGER.debug("***Incorrect edit..return to edit page");
            model.put("userId", id);
            model.put("targetId", targetId);
            return "editpage";
        }

        try{
            if (id.equals(targetId)) {

                if(checkPass(editModel, id, model, targetId)){
                    return "editpage";
                }

                userService.editUser(editModel, targetId);
                LOGGER.info("***User \'"+ id +"\' profile was changed");
            } else {
                contactService.editContact(editModel, targetId);
                LOGGER.info("***Contact was changed");
            }
        } catch (EditException e){
            model.put("loginMessage", e.getMessage());
            model.put("userId", id);
            model.put("targetId", targetId);
            return "editpage";
        }


        User user = userService.findUser(Integer.parseInt(id));
        model.put("user", user);

        return "homepage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id", required = true) String id,
                             @RequestParam(value = "targetId", required = true) String targetId,
                             Map<String, Object> model) {

        LOGGER.debug("***Enter in deletePost method");

        Contact contact = contactService.remove(targetId);
        LOGGER.info("***Contact was removed : " + contact);

        User user = userService.findUser(Integer.parseInt(id));
        model.put("user", user);
        return "homepage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@RequestParam(value = "id", required = true) String id,
                          Map<String, Object> model) {

        LOGGER.debug("***Enter in addPost method");


        model.put("userId", id);
        model.put("newContact", new Contact());
        model.put("message", "input your new contact");

        return "add";
    }

    @RequestMapping(value = "/addcontact", method = RequestMethod.POST)
    public String addContactPost(@RequestParam(value = "id", required = true) String id,
                                 @ModelAttribute("newContact") @Valid Contact contact,
                                 BindingResult result, Map<String, Object> model) {


        LOGGER.debug("***Enter in addContactPost method");

        if(result.hasErrors()) {
            LOGGER.debug("***Incorrect to add..return to add page");
            model.put("message", "Incorrect");
            model.put("userId", id);
            return "add";
        }

        try {

            User user = userService.findUser(Integer.parseInt(id));
            Contact newContact = new Contact(contact, user);
            user.getPhoneBook().add(newContact);

            LOGGER.debug("***User : " + id);
            LOGGER.debug("***Want to save : " + contact);

            contactService.saveContact(newContact);
            LOGGER.info("***New contact was saved");

        } catch (RegistrationException e) {

            LOGGER.error("***Exception during save contact : ", e);
            model.put("loginMessage", e.getMessage());
            model.put("userId", id);
            return "add";
        }

        User user = userService.findUser(Integer.parseInt(id));
        model.put("user", user);
        return "homepage";
    }

    private boolean checkPass(EditModel editModel, String id, Map<String, Object> model, String targetId) {

        User user = userService.findUser(Integer.parseInt(id));
        if(!user.getPass().equals(editModel.getPass())){
            model.put("oldPassMessage", "Passwords must be the same");
            model.put("userId", id);
            model.put("targetId", targetId);
            return true;
        }

        if(!editModel.getNewPass().equals(editModel.getRePass())){
            LOGGER.debug("***Different passwords.. return to edit page");
            model.put("newPassMessage", "Your passwords are different");
            model.put("userId", id);
            model.put("targetId", targetId);
            return true;
        }
        return false;
    }

}
