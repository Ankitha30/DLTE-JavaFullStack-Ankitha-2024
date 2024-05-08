import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.database.DatabaseService;
import org.entity.AddressDetails;
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
import java.util.stream.Collectors;

@WebServlet("/add/*")
public class AddEmployee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            Activity employeeInterface = new DatabaseService();
            String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            // Deserialize the request body into Employee and AddressDetails objects
            JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
            Employee employee = gson.fromJson(jsonObject, Employee.class);
            AddressDetails permanent = gson.fromJson(jsonObject.getAsJsonObject("permanentAddress"), AddressDetails.class);
            AddressDetails temporary = gson.fromJson(jsonObject.getAsJsonObject("temporaryAddress"), AddressDetails.class);
            employeeInterface.addEmployeePersonalDetails(employee, permanent, temporary);
        } catch (ConnectionException | EmployeeException e) {
            e.printStackTrace();
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Employee added");

    }
}
