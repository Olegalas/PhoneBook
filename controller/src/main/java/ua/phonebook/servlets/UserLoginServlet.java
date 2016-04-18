package ua.phonebook.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.phonebook.exceptions.LoginException;
import ua.phonebook.service.UserService;
import ua.phonebook.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dexter on 17.04.16.
 */
@WebServlet(urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet{

    private static final Logger LOG = Logger.getLogger(UserLoginServlet.class);

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
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        try {

            User user = userService.login(login, pass);
            HttpSession session = req.getSession(true);
            session.setAttribute("inSystem", true);
            session.setAttribute("currentUser", user);

            resp.sendRedirect("home.jsp");

        } catch (LoginException e) {
            LOG.error(e);
            // return to login page with error message
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
        }

    }

}
