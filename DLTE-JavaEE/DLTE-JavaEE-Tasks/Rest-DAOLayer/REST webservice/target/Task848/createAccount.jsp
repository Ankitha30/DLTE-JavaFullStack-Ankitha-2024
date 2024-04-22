<%--
  Created by IntelliJ IDEA.
  User: xxmonise
  Date: 4/22/2024
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("username") != null) {
%>
<nav class="navbar navbar-expand-lg" style="background:linear-gradient(90deg,greenyellow,yellow,greenyellow)">
    <div class="container-fluid">
        <!-- 1st logo/ brand -->
        <a class="navbar-brand text-danger  text-uppercase" style="font-weight: bold;" href="#">MyBank</a>
        <!-- 2nd toggle a -->
        <a class="navbar-toggler bg-light" type="a" data-bs-toggle="collapse" data-bs-target="#myBankMenu">
            <span class="navbar-toggler-icon"></span>
        </a>
        <!-- 3rd Menu -->
        <div class="collapse navbar-collapse" id="myBankMenu">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <p class="text-light " style="font-weight: bold">Hi, <%=session.getAttribute("username")%></p>
                </li>&nbsp;
                <li class="nav-item">
                    <a href="view" class="btn btn-outline-white rounded-5 me-2" style="font-weight: bolder"><span class="bi bi-list-columns"></span> View</a>
                </li>
                <li class="nav-item">
                    <a href="createAccount.jsp" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-cloud-plus-fill"></span> New</a>
                </li>
                <li class="nav-item">
                    <a href="logout" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-door-open"></span> Logout</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<%
    String info = (String) request.getAttribute("info");
%>

<div class="container">
    <% if (info != null && info != "") { %>
    <h1 class="text-center text-success"><%= info %></h1>
    <% } %>
    <h1>Create Account</h1>
    <form action="add" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="number" id="phoneNumber" name="phoneNumber" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<% } else {
    response.sendRedirect("index.jsp");
} %>

</body>
</html>