<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sikeres regisztráció!</title>
</head>
<body>
    <h2>Sikeresen regisztráltál, kedves ${requestScope.user.firstName}!</h2>
    <% for (int i = 0; i < 10; i++) { %>
        <p><%=i%></p>
    <% } %>
</body>
</html>
