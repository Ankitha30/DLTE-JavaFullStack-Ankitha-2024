<%--
  Created by IntelliJ IDEA.
  User: xxbhatka
  Date: 4/22/2024
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="app.mybank.entity.Transaction" %>
<html>
<head>
    <title>View All Transactions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        /* Additional CSS styles for table alignment and size */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Adjust height as needed */
        }

        .custom-table {
            width: 100%; /* Adjust table width as needed */
        }
    </style>

</head>
<body>
<% response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
    System.out.println(session.getAttribute("username:   " + "username"));
    if (session.getAttribute("username") != null) {%>
<nav class="navbar navbar-expand-lg" style="background:linear-gradient(90deg,greenyellow,yellow,greenyellow);">
    <div class="container-fluid">
        <a class="navbar-brand text-info text-uppercase" style="font-weight:bold;" href="#"></a>
        <a class="navbar-toggler bg-light" type="a" data-bs-toggle="collapse" data-bs-target="#myBankMenu">
            <span class="navbar-toggler-icon"></span>
        </a>
        <div class="collapse navbar-collapse" id="myBankMenu">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <p class="text-light display-6">Hi, <%=session.getAttribute("username")%>
                    </p>
                </li>
                <li class="nav-item">
                    <a href="view" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-list-columns"></span>
                        View</a>
                </li>
                <li class="nav-item">
                    <a href="viewTransactions.jsp" class="btn btn-outline-white rounded-5 me-2"
                       style="font-weight: bolder"><span class="bi bi-list-columns"></span> View By type and User</a>
                </li>
                <li class="nav-item">
                    <a href="createAccount.jsp" class="btn btn-outline-light rounded-5 me-2"><span
                            class="bi bi-cloud-plus-fill"></span> New</a>
                </li>
                <li class="nav-item">
                    <a href="logout" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-door-open"></span>
                        Logout</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<%
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("myTransactions");
    pageContext.setAttribute("myData", transactions, PageContext.APPLICATION_SCOPE);
%>
<div class="container">
    <div class="col-lg-4 col-md-8 col-12 table-responsive p-5 shadow-lg">
        <table class="table table-stripped custom-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <% for (Transaction each : transactions) { %>

            <tr>
                <% if (each.getUserName().equals(session.getAttribute("username"))) {%>
                <td><%out.print(each.getUserName());%></td>
                <td><%out.print(each.getTransactionType());%></td>
                <td><%out.print(each.getTransactionAmount());%></td>
                <td><%out.print(each.getTransactionDate());%></td>


            </tr>
            <%}%>
            <%}%>
            </tbody>

        </table>
    </div>
</div>
</div>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>
}
</body>
</html>
