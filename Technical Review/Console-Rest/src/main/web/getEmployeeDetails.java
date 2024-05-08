package org.web;

import com.google.gson.Gson;
import org.database.DatabaseService;
import org.entity.Employee;
import org.exceptions.ConnectionException;
import org.middleware.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
''

@WebServlet("/display/*")
public class getEmployeeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Activity employeeInterface = new DatabaseService();
            List<Employee> employees = employeeInterface.getEmployees();
            Gson gson = new Gson();
            String response = gson.toJson(employees);
            resp.getWriter().println(response);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
}
