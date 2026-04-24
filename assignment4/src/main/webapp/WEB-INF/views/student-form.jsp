<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>
</head>
<body>
<h2>Student Form</h2>
<form:form method="post"
           modelAttribute="student"
           action="${pageContext.request.contextPath}${student.id == null ? '/students' : '/students/update'}">
    <form:hidden path="id"/>
    <p>
        <label>Name:</label><br/>
        <form:input path="name"/>
    </p>
    <p>
        <label>Email:</label><br/>
        <form:input path="email"/>
    </p>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/students">Cancel</a>
</form:form>
</body>
</html>
