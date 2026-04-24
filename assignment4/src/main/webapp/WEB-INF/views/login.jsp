<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <p>
        <label for="email">Email:</label><br/>
        <input type="email" id="email" name="email" value="${email}" required/>
    </p>
    <p>
        <label for="password">Password:</label><br/>
        <input type="password" id="password" name="password" required/>
    </p>
    <button type="submit">Login</button>
</form>
</body>
</html>
