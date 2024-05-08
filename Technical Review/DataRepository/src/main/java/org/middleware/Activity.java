package org.middleware;

import org.entity.*;
import org.exceptions.*;
import org.entity.AddressDetails;

import java.util.List;

public interface Activity {

//    void addEmployees(List<EmployeeDetails> empList);// change the return type so that user can get the confirmation of data added to file successfully

    boolean addEmployeePersonalDetails(Employee employee, AddressDetails permanent, AddressDetails temp) throws EmployeeException;

    //    boolean addPermanentAddressDetails(AddressDetails addressDetails) throws EmployeeException;
//    boolean addTemporaryAddressDetails(AddressDetails addressDetails) throws EmployeeException;
    boolean doesEmployeeExists(String id);

    Employee getEmployeeById(String employeeId);

    List<Employee> employeeByPinCode(String pinCode);

    List<Employee> getEmployees();

    void closeResources();
}
