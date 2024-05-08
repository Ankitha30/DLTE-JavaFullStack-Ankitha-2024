package backend.middleware;
import java.util.List;
import org.middleware.PersonalDetails;
public interface Activity  {

//    void addEmployees(List<EmployeeDetails> empList);// change the return type so that user can get the confirmation of data addede to file successfully
    void  addPermanentAddressDetails(AddressDetails addressDetails);
    void  addTemporaryAddressDetails(AddressDetails addressDetails);
    void addEmployeePersonalDetails(PersonalDetails personalDetails);
    EmployeeDetails getEmployeeById(int employeeId);
    List<EmployeeDetails> employeeByPinCode(String pinCode);
    List<EmployeeDetails> getEmployees();
}
