package ua.phonebook.controller.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import ua.phonebook.controller.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dexter on 17.04.16.
 */
@WebServlet(urlPatterns = "/home")
public class UserHomeServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserHomeServlet.class);

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








    }

}
