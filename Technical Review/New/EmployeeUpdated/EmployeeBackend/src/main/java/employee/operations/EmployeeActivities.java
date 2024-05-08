package employee.operations;
import entity.*;

import java.util.List;

public interface EmployeeActivities {
    void  addAddressDetails(AddressDetails addressDetails);
    void addEmployeePersonalDetails(PersonalDetails personalDetails);
    EmployeeDetails getEmployeeById(int employeeId);
    List<EmployeeDetails> employeeByPinCode(String pinCode);
    List<EmployeeDetails> getEmployees();
}
