<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>University Login</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;">${error}</p>
    <% } %>

    <form action="login" method="post">
        <label>Email:</label>
        <input type="email" name="email" required/><br/><br/>
        <label>Password:</label>
        <input type="password" name="password" required/><br/><br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>