package com.jdbc.springcomponent.Repository;

import com.jdbc.springcomponent.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepo {

//    void addEmployees(List<EmployeeDetails> empList);// change the return type so that user can get the confirmation of data added to file successfully

    Employee apiSave(Employee employee);

    //    boolean addPermanentAddressDetails(AddressDetails addressDetails) throws EmployeeException;
//    boolean addTemporaryAddressDetails(AddressDetails addressDetails) throws EmployeeException;
    boolean apiDoesEmployeeExists(String id);

    Employee apiGetEmployeeById(String employeeId);

    List<Employee> apiEmployeeByPinCode(String pinCode);

    List<Employee> apiGetEmployees();

//    void closeResources();
}
