package org.middleware;

import java.util.List;

public interface Activity  {

//    void addEmployees(List<EmployeeDetails> empList);// change the return type so that user can get the confirmation of data addede to file successfully
    void  addAddressDetails(AddressDetails addressDetails);
    void addEmployeePersonalDetails(PersonalDetails personalDetails);
    EmployeeDetails getEmployeeById(int employeeId);
    List<EmployeeDetails> employeeByPinCode(String pinCode);
    List<EmployeeDetails> getEmployees();
}
