package lk.pdn.ac;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Map<String, String> userDB = new HashMap<>();

    @Override
    public void init() throws ServletException {
        
        userDB.put("student1", "pass1");
        userDB.put("student2", "pass2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userDB.containsKey(username) && userDB.get(username).equals(password)) {
            
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(30 * 60); 
            response.addCookie(userCookie);

            response.sendRedirect("dashboard");
        } else {
            response.getWriter().println("Invalid credentials. Please <a href=\"login.html\">try again</a>.");
        }
    }
}
