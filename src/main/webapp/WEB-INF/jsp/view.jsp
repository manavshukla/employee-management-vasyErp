<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Employees</title>
    <style>
        body {
            background: #f4f7f8;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 40px 20px;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 1000px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #2c3e50;
        }

        .message {
            color: #27ae60;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a.action-link {
            color: #2980b9;
            text-decoration: none;
            margin-right: 10px;
        }

        a.action-link:hover {
            text-decoration: underline;
        }

        .button-link {
            display: inline-block;
            margin-top: 10px;
            margin-right: 10px;
            padding: 10px 18px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        .button-link:hover {
            background-color: #2980b9;
        }

        .no-data {
            text-align: center;
            color: #7f8c8d;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>All Employees</h2>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <c:if test="${not empty employees}">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Email</th>
                <th>Salary</th>
                <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                    <th>Actions</th>
                </c:if>
            </tr>
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                    <td>${emp.email}</td>
                    <td>${emp.salary}</td>
                    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                        <td>
                            <a class="action-link" href="/api/employee/update/${emp.id}">Edit</a>
                            <a class="action-link" href="/api/employee/delete/${emp.id}"
                               onclick="return confirm('Are you sure you want to delete this employee?');">
                               Delete
                            </a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty employees}">
        <p class="no-data">No employees found.</p>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
        <a class="button-link" href="/api/employee/add">Add Employee</a>
    </c:if>
    <a class="button-link" href="/api/employee/home">Back to Home</a>
</div>

</body>
</html>
