package ua.phonebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.model.User;
import ua.phonebook.service.UserService;

import javax.annotation.Resource;

/**
 * Created by dexter on 17.04.16.
 */
@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstPage(Model model) {

        LOGGER.debug("***Enter in firstPage method");

        model.addAttribute("message", "Enter your login and pass");
        return "loginpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "login", required = true) String login,
                            @RequestParam(value = "pass", required = true) String pass, Model model) {

        LOGGER.debug("***Enter in loginPost method");

        try {
            User user = service.login(login, pass);
            LOGGER.info("User entered in.. user login - " + user.getLogin());
            model.addAttribute("user", user);
            return "home";
        } catch (LoginException e) {
            LOGGER.error("***LoginException : ", e);
            model.addAttribute("message", e.getMessage());
            return "loginPage";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(Model model) {

        LOGGER.debug("***Enter in registrationGet method");
        model.addAttribute("message", "Sign up");
        return "registrationpage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("personAttribute") User user, Model model) {

        LOGGER.debug("***Enter in registrationPost method");


        try {
            service.registration(user);
            LOGGER.info("User has been saved");
            model.addAttribute("message", "Enter your login and pass");
            return "loginPage";
        } catch (RegistrationException e) {
            LOGGER.error("***LoginException : ", e);
            model.addAttribute("message", e.getMessage());
            return "registrationpage";
        }
    }

}
