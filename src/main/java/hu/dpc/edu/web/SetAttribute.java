package hu.dpc.edu.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vrg on 21/10/16.
 */
@WebServlet("/set")
public class SetAttribute extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String scope = req.getParameter("scope");
        final String value = req.getParameter("value");

        switch (scope) {
            case "request": req.setAttribute(name, value);
                break;
            case "session": req.getSession().setAttribute(name, value);
                break;
            case "application": getServletContext().setAttribute(name, value);
                break;
            default:

        }

        final PrintWriter out = resp.getWriter();
        out.println("Setting " + scope + "." + name + " = " + value);
    }
}
