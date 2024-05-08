package org.database;
import org.middleware.EmployeeDetails;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DatabaseService service=new DatabaseService();
        List<EmployeeDetails> employeeDetails = service.employeeByPinCode("574274");
        employeeDetails.forEach(System.out::println);
    }
}
