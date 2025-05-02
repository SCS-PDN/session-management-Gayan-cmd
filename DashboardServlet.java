package lk.pdn.ac;

import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("C101", "Web Development", "Dr. Smith"));
        courses.add(new Course("C102", "Data Structures", "Prof. Allen"));
        courses.add(new Course("C103", "Algorithms", "Dr. Johnson"));

        request.setAttribute("courseList", courses);

        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}