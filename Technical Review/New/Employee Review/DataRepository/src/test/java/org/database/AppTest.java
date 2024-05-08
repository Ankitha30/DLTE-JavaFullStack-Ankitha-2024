package org.database;


//import org.database.DatabaseService;
//import org.entity.AddressDetails;
//import org.entity.Employee;
//import org.exceptions.ConnectionException;
//import org.exceptions.EmployeeException;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;

public class AppTest {

//    @Mock
//    private Connection connection;
//
//    @Mock
//    private PreparedStatement preparedStatement;
//
//    @InjectMocks
//    private DatabaseService databaseService;
//
//    @Before
//    public void setUp() throws SQLException {
//        MockitoAnnotations.initMocks(this);
//        try {
//            databaseService = new DatabaseService();
//        } catch (ConnectionException e) {
//            fail("Failed to connect to the database");
//        }
//
//    }
//    @After
//    public void tearDown() {
//        databaseService.closeResources();
//    }
//
//    @Test
//    public void testAddEmployeePersonalDetails_Success() throws SQLException, EmployeeException, ConnectionException {
//        Employee employee = new Employee("Anu", "S", "Bhat", "Snu@gmail.com", "7780987654", "12349");
//        AddressDetails permanentAddress = new AddressDetails("100", "Banangadi", "Moodbidre", "Karnataka", "574267","12349");
//        AddressDetails temporaryAddress = new AddressDetails("400", "&th cross", "vijaya nagar", "Karnataka", "574299","12349");
////        Employee employee = new Employee("Anu", "S", "Bhat", "Snu@gmail.com", "7780987654",permanentAddress,temporaryAddress, "12349");
//
//        boolean result = databaseService.addEmployeePersonalDetails(employee);
//        boolean permanentResult = databaseService.addPermanentAddressDetails(permanentAddress);
//        boolean tempResult= databaseService.addTemporaryAddressDetails(temporaryAddress);
//
//        assertNotNull(result);
//        assertNotNull(tempResult);
//        assertNotNull(permanentResult);
//        assertEquals(true, result);
////
//    }
//    @Test
//    public void testDoesEmployeeExists() throws SQLException, EmployeeException, ConnectionException {
//        boolean result=databaseService.doesEmployeeExists("12349");
//        assertTrue(result); // pass
//        assertEquals(true,false); // fail
//    }
//
//
//    @Test(expected = EmployeeException.class)
//    public void testAddEmployeePersonalDetails_InvalidEmail_ExceptionThrown() throws SQLException, EmployeeException {
//        Employee employee = new Employee("John", "D", "Smith", "invalid_email", "1234567890", "12345");
//
//        databaseService.addEmployeePersonalDetails(employee);
//
//    }
//    @Test
//    public void testDoesEmployeeExists_True() throws SQLException {
//        Employee employee = new Employee("John", "D", "Smith", "invalid_email", "1234567890", "12345");
//
//        boolean result = databaseService.doesEmployeeExists("12345");
//
//        System.out.println("Result: " + result); // Add logging statement
//
//        assertFalse(result); // Assertion failure occurs here
////        assertTrue(result); // Assertion passed
////        verify(connection).prepareStatement(anyString());
//        // Verify other interactions as needed
//    }
//
//    @Test
//    public void testEmployeeByPinCode() {
//        List<Employee> employeeList= databaseService.employeeByPinCode("574274");
//        assertNotNull(employeeList);
////        boolean result = employeeList.isEmpty();
////        System.out.println(result);
////        assertFalse(employeeList.isEmpty());// pass
//    }
//
//    @Test
//    public void testDisplayAll(){
//        AddressDetails permanentAddress = new AddressDetails("100", "Banangadi", "Moodbidre", "Karnataka", "574267","12349");
//        AddressDetails temporaryAddress = new AddressDetails("400", "&th cross", "vijaya nagar", "Karnataka", "574299","12349");
//        Employee employee = new Employee("Anu", "S", "Bhat", "Snu@gmail.com", "7780987654",permanentAddress,temporaryAddress, "12349");
//        AddressDetails permanentAddress1 = new AddressDetails("200-7", "Gundyadka", "Paladka", "Karnataka", "574267","234567");
//        AddressDetails temporaryAddress1 = new AddressDetails("400", "1st cross", "Kodyadka", "Karnataka", "574299","234567");
//        Employee employee1 = new Employee("Anusha", "p", "Bhat", "anusha@gmail.com", "7780987654",permanentAddress,temporaryAddress, "234567");
//
//        // Mocking the databaseService
//        DatabaseService databaseService = mock(DatabaseService.class);
//
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(employee); // Adding employee to the list
//        employeeList.add(employee1);
//        when(databaseService.getEmployees()).thenReturn(employeeList); // Stubbing the getEmployees method
//
//        List<Employee> result = databaseService.getEmployees(); // Invoking the method
//
//        // Verifying that the size of the result list matches the size of the employeeList
////        assertEquals(employeeList.size(), result.size());
////        assertEquals(employeeList.get(0).getEmployeeId(), result.get(0).getEmployeeId());
//        assertSame(employeeList.get(0).getEmployeeId(), result.get(0).getEmployeeId());
//    }
//

}
