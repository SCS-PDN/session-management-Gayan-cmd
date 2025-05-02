<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        table { border-collapse: collapse; width: 70%; margin-bottom: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        .top-bar { display: flex; justify-content: space-between; align-items: center; }
    </style>
</head>
<body>
    <div class="top-bar">
        <div>
            <h2>Welcome to the Dashboard</h2>
            <%
                String username = (String) session.getAttribute("username");
                if (username != null) {
            %>
                <p>Logged in as: <strong><%= username %></strong></p>
            <% } %>
        </div>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>

    <% String msg = request.getParameter("msg"); %>
    <% if (msg != null) { %>
        <p style="color: green;"><%= msg %></p>
    <% } %>

    <h3>Available Courses</h3>
    <table>
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <%
            List<Course> courseList = (List<Course>) request.getAttribute("courseList");
            for (Course course : courseList) {
        %>
            <tr>
                <td><%= course.getCourseId() %></td>
                <td><%= course.getCourseName() %></td>
                <td><%= course.getInstructor() %></td>
                <td><a href="enroll?courseId=<%= course.getCourseId() %>">Enroll</a></td>
            </tr>
        <%
            }
        %>
    </table>

    <h3>Enrolled Courses</h3>
    <%
        List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolled != null && !enrolled.isEmpty()) {
    %>
        <table>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Instructor</th>
            </tr>
            <% for (Course course : enrolled) { %>
            <tr>
                <td><%= course.getCourseId() %></td>
                <td><%= course.getCourseName() %></td>
                <td><%= course.getInstructor() %></td>
            </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>You have not enrolled in any courses yet.</p>
    <% } %>
</body>
</html>
