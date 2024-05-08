import com.google.gson.Gson;
import org.database.App;
import org.database.DatabaseService;
import org.entity.Employee;
import org.exceptions.ConnectionException;
import org.middleware.Activity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/display/*")
public class getEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Employee> employees = new ArrayList<>();
        try {
//            Activity employeeInterface = new DatabaseService();
            Activity employeeInterface = null;
            employees = employeeInterface.getEmployees();
            Gson gson = new Gson();
            String response = gson.toJson(employees);
            resp.getWriter().println(response);
            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.setStatus(101, " Success OK");
        } catch (Exception e) {
//            resp.setStatus(102, "Custom Error ");
//            Employee employee = new Employee();
//            Gson gson = new Gson();
//            String response = gson.toJson(employee);
//            resp.getWriter().println(response);
////            System.out.println("done");
////            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

