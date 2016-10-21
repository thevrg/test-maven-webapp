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
@WebServlet(name="myServlet", urlPatterns = "/first")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String name = req.getParameter("name");
        final String accept = req.getHeader("Accept");
        final Cookie[] cookies = req.getCookies();

        final String cookieName = req.getParameter("cookieName");
        final String cookieValue = req.getParameter("cookieValue");

        if (cookieName != null && !cookieName.isEmpty()) {
            Cookie newCookie = new Cookie(cookieName, cookieValue);
            newCookie.setMaxAge(3600);
            resp.addCookie(newCookie);
        }


        resp.setContentType("text/plain");


        try(final PrintWriter out = resp.getWriter()) {
            out.println("ContextPath: " + req.getContextPath());
            out.println("Hello, dear " + name + "!");
            out.println("Accept header: " + accept + "!");
            for (Cookie cookie : cookies) {
                out.println("Cookie found: " + cookie.getName() + " = " + cookie.getValue());
            }

        }
    }
}
