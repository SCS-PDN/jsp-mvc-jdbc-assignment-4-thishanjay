<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Courses</title>
</head>
<body>
    <h2>Welcome, ${sessionScope.loggedInStudent.name}!</h2>
    <a href="logout">Logout</a>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;">${error}</p>
    <% } %>

    <h3>Available Courses</h3>
    <table border="1">
        <tr>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Credits</th>
            <th>Action</th>
        </tr>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.name}</td>
                <td>${course.instructor}</td>
                <td>${course.credits}</td>
                <td>
                    <form action="register/${course.courseId}" method="post">
                        <input type="submit" value="Register"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>