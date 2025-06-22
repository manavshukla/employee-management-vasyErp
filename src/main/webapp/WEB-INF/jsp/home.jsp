<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - Employee Management System</title>
    <style>
        body {
            background: #f4f7f8;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .home-container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 500px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #2c3e50;
        }

        .message {
            margin-bottom: 20px;
            font-weight: bold;
            color: #27ae60;
        }

        a {
            display: block;
            margin: 10px 0;
            text-decoration: none;
            color: #3498db;
            font-weight: 500;
            transition: color 0.3s;
        }

        a:hover {
            color: #2980b9;
        }

        .logout {
            margin-top: 20px;
            display: inline-block;
            background-color: #e74c3c;
            color: white;
            padding: 10px 20px;
            border-radius: 8px;
            font-weight: bold;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .logout:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

<div class="home-container">
    <h2>Welcome, ${pageContext.request.userPrincipal.name}</h2>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
        <a href="/api/employee/add">➕ Add Employee</a>
        <a href="/api/employee/viewAll">📋 View All Employees</a>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('USER')}">
        <a href="/api/employee/viewAll">📋 View All Employees</a>
    </c:if>

    <a class="logout" href="/logout">Logout</a>
</div>

</body>
</html>
