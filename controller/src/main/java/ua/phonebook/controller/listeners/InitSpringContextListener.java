package ua.phonebook.controller.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by dexter on 16.04.16.
 */
public class InitSpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String springLocation = servletContextEvent.getServletContext().getInitParameter("springLocation");

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-context.xml");

        servletContextEvent.getServletContext().setAttribute("spring-context",applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
