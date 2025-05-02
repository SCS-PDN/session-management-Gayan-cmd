package lk.pdn.ac;

import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);
        if (courseId == null || session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        
        List<Course> allCourses = Arrays.asList(
                new Course("C101", "Web Development", "Dr. Smith"),
                new Course("C102", "Data Structures", "Prof. Allen"),
                new Course("C103", "Algorithms", "Dr. Johnson")
        );

        
        Course selectedCourse = null;
        for (Course course : allCourses) {
            if (course.getCourseId().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            
            List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolled == null) {
                enrolled = new ArrayList<>();
            }

            
            boolean alreadyEnrolled = false;
            for (Course c : enrolled) {
                if (c.getCourseId().equals(courseId)) {
                    alreadyEnrolled = true;
                    break;
                }
            }

            if (!alreadyEnrolled) {
                enrolled.add(selectedCourse);
                session.setAttribute("enrolledCourses", enrolled);
                response.sendRedirect("dashboard?msg=Successfully enrolled in " + selectedCourse.getCourseName());
            } else {
                response.sendRedirect("dashboard?msg=Already enrolled in " + selectedCourse.getCourseName());
            }
        } else {
            response.sendRedirect("dashboard?msg=Course not found");
        }
    }
}
