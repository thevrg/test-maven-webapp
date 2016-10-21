package hu.dpc.edu.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vrg on 21/10/16.
 */
@WebServlet("/register")
public class RegisterUserController extends HttpServlet {

    private String szin;

    @Override
    public void init() throws ServletException {
        szin = getInitParameter("szin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String first = req.getParameter("first");
        final String last = req.getParameter("last");


        final User user = new User(szin + " " + first, last);

        req.setAttribute("user", user);

        final ServletContext servletContext = getServletContext();

//        servletContext.setAttribute();


        final RequestDispatcher requestDispatcher = getServletContext().getNamedDispatcher("siker");

        //final RequestDispatcher requestDispatcher = req.getRequestDispatcher("successfulRegistration");

        //passing dispatching to....
        requestDispatcher.forward(req,resp);
        System.out.println("Hello from controller...");
    }
}
