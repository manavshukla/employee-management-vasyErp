<h2>My Profile</h2>
<c:if test="${employee != null}">
    <table border="1">
        <tr><td>ID</td><td>${employee.id}</td></tr>
        <tr><td>Name</td><td>${employee.name}</td></tr>
        <tr><td>Department</td><td>${employee.department}</td></tr>
        <tr><td>Email</td><td>${employee.email}</td></tr>
        <tr><td>Salary</td><td>${employee.salary}</td></tr>
    </table>
</c:if>
<c:if test="${employee == null}">
    <p>No employee record found for your login.</p>
</c:if>
<a href="/api/employee/home">Back to Home</a>
