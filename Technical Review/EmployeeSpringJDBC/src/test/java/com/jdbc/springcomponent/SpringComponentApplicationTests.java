package com.jdbc.springcomponent;

import com.jdbc.springcomponent.entity.AddressDetails;
import com.jdbc.springcomponent.entity.Employee;
import com.jdbc.springcomponent.services.EmployeeService;
import com.jdbc.springcomponent.services.EmployeeService.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringComponentApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    EmployeeService employeeService;

//    @Test
//public void testAllEmployee(){
//    AddressDetails taddressDetails= new AddressDetails();
//    taddressDetails.setHouseNumber("3445");
//    taddressDetails.setStreetAddress("road");
//    taddressDetails.setCityName("moodbidri");
//    taddressDetails.setState("karnataka");
//    taddressDetails.setAddressPin("574247");
//    AddressDetails paddressDetails= new AddressDetails();
//    paddressDetails.setHouseNumber("651");
//    paddressDetails.setStreetAddress("road permanent");
//    paddressDetails.setCityName("alangar");
//    paddressDetails.setState("karnataka");
//    paddressDetails.setAddressPin("574272");
//    Employee employee= new Employee();
//    employee.setTemporaryAddress(taddressDetails);
//    employee.setPermanentAddress(paddressDetails);
//    employee.setEmployeeId("123456");
//    employee.setEmployeeMobileNumber("1234567890");
//    employee.setEmployeeEmail("asd@asd.asd");
//    employee.setEmployeeLastName("qwe");
//    employee.setEmployeeMiddleName("dfg");
//    employee.setEmployeeFirstName("zxc");
//    List<Employee> employeeList = new ArrayList<>();
//   AddressDetails t1 = new AddressDetails("321","asd","rd1","Karnataka","576345");
//   AddressDetails p2 =new AddressDetails("456","lkj","mnb","karnataka","345768");
//   Employee employee1 = new Employee("zse","fgc","aks","zxv@zc.sa","2345678909",t1,p2,"234579");
//   when(jdbcTemplate.query(anyString(),any(EmployeeService.EmployeeMapper.class))).thenReturn(employeeList);
//
//        employeeList.add(employee);
//   employeeList.add(employee1);
////    System.out.println(employeeList);
//        employeeList.forEach(System.out::println);
//   List<Employee> result = employeeService.apiGetEmployees();
//                given(jdbcTemplate.query(eq("SELECT e.employee_id, e.first_name, e.middle_name, e.last_name, e.email, e.mobile_number, \" +\n" +
//                                "                \"t.house_number AS temporaryHouse, t.street AS temporaryStreet, t.city AS temporaryCity, t.state AS temporaryState, t.pin AS temporaryPin, \" +\n" +
//                                "                        \"p.house_number AS permanentHouse, p.street AS permanentStreet, p.city AS permanentCity, p.state AS permanentState, p.pin AS permanentPin \" +\n" +
//                                "                        \"FROM personal_detail e \" +\n" +
//                                "                        \"INNER JOIN address_details t ON e.employee_id = t.employee_id AND t.is_permanent = 0 \" +\n" +
//                                "                        \"INNER JOIN address_details p ON e.employee_id = p.employee_id AND p.is_permanent = 1"),
//       eq(new Object[]{}), any(EmployeeService.EmployeeMapper.class))).willReturn(employeeList);
//
//        result.forEach(System.out::println);
//
//        System.out.println(result.toString());
////   assertEquals("zse",result.getEmployeeFirstName());
//

//}


    private List<Employee> createMockEmployees() {
        List<Employee> employees = new ArrayList<>();

        // Mock Employee objects for testing
        AddressDetails tempAddress = new AddressDetails("1", "Temp St", "Temp City", "Temp State", "12345");
        AddressDetails permAddress = new AddressDetails("2", "Perm St", "Perm City", "Perm State", "67890");

        Employee employee1 = new Employee("1", "John", "Doe", "john.doe@example.com", "1234567890", tempAddress, permAddress,"123465");
        Employee employee2 = new Employee("2", "Jane", "Smith", "jane.smith@example.com", "9876543210", tempAddress, permAddress,"412345");

        employees.add(employee1);
        employees.add(employee2);

        return employees;
    }
    @Test
    public void testApiGetEmployees_employeeFound() throws SQLException {
        List<Employee> mockEmployees = createMockEmployees();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(EmployeeService.EmployeeMapper.class))).thenReturn(mockEmployees);

        // Invoke the method under test
        List<Employee> result = employeeService.apiGetEmployees();

        // Verify the result
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(EmployeeService.EmployeeMapper.class));
//        assert !result.isEmpty();
        assertEquals("John", result.get(0).getEmployeeMiddleName());
//        assert result.size() == mockEmployees.size(); // Assuming the size matches
    }

}
