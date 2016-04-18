package ua.phonebook.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.phonebook.exceptions.RegistrationException;
import ua.phonebook.service.UserService;
import ua.phonebook.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dexter on 16.04.16.
 */
@WebServlet(urlPatterns = "/registration")
public class UserRegistrationServlet extends HttpServlet{

    private static final Logger LOG = Logger.getLogger(UserRegistrationServlet.class);

    private ApplicationContext applicationContext;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        applicationContext =
                (ApplicationContext) getServletContext().getAttribute("spring-context");
        userService = applicationContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  some action
        // just redirect to register.jsp
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // waiting data from the form
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String mobileTelephone = req.getParameter("mobileTelephone");
        String homeTelephone = req.getParameter("homeTelephone");

        User user = new User();
        user.setLogin(login);
        user.setPass(pass);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMobileTelephone(mobileTelephone);
        user.setHomeTelephone(homeTelephone);

        try {
            User created = userService.registration(user);
            req.setAttribute("user", created);
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
        } catch (RegistrationException e) {
            LOG.error(e);
            req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req,resp);
            // forward to a error page
        }

    }

}
