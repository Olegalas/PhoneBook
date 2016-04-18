package com.olegalas.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.phonebook.controller.exceptions.LoginException;
import ua.phonebook.controller.service.UserService;

import javax.annotation.Resource;

/**
 * Created by dexter on 17.04.16.
 */
@Controller
@RequestMapping("/main")
public class MainController {


    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Resource(name="personService")
    private UserService service;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value="login", required=true) String login,
                        @RequestParam(value="pass", required=true) String pass) {

        LOGGER.info("***Enter in login method");

        try {
            service.login(login, pass);
        } catch (LoginException e) {
            LOGGER.error("***LoginException : " + e.getMessage());
        }
        return "home";
    }
}
