<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
</head>
<body>
<h2>Registration Successful</h2>
<p><strong>${userEmail}</strong>, you have successfully registered for:</p>
<p><strong>${course.code} - ${course.title}</strong> (${course.credits} credits)</p>

<p>
    <a href="${pageContext.request.contextPath}/courses">Back to Courses</a> |
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</p>
</body>
</html>
