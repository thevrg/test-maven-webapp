package hu.dpc.edu.web;

import javax.servlet.ServletException;
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
@WebServlet(name = "siker", urlPatterns = "/valami")
public class SuccessfulRegistrationView extends HttpServlet {
    String ize;
    @Override
    public void init() throws ServletException {
        ize = getServletContext().getInitParameter("ize");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getAttribute("user");

        resp.setContentType("text/plain");

        final PrintWriter out = resp.getWriter();
            out.println("You are successfully registered, dear " + user.getFirstName() + "! " + ize);

    }
}
