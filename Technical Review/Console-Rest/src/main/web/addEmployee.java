package org.web;

import com.google.gson.Gson;
import org.database.DatabaseService;
import org.entity.Employee;
import org.exceptions.ConnectionException;
import org.exceptions.EmployeeException;
import org.middleware.Activity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add/*")
public class addEmployee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            Activity employeeInterface = new DatabaseService();
            Employee employee = gson.fromJson(req.getReader(), Employee.class);
            employeeInterface.addEmployeePersonalDetails(employee);
        } catch (ConnectionException | EmployeeException e) {
            e.printStackTrace();
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Employee added");

    }
}
