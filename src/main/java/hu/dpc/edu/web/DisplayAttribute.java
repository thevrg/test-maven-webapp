package hu.dpc.edu.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vrg on 21/10/16.
 */
@WebServlet("/display")
public class DisplayAttribute extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String scope = req.getParameter("scope");

        Object value;

        switch (scope) {
            case "request": value = req.getAttribute(name);
                break;
            case "session":
                final HttpSession session = req.getSession();
                final String sessionId = session.getId();
                resp.encodeURL("alma.html");
                value = session.getAttribute(name);
                break;
            case "application": value = getServletContext().getAttribute(name);
                break;
            default:
                value = "No such scope";
        }

        final PrintWriter out = resp.getWriter();
        out.println(scope + "." + name + " = " + value);
    }
}
