package org.database;

import org.exceptions.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ConnectionException {
        DatabaseService databaseService = new DatabaseService();
        System.out.println("Hello World!");
//        System.out.println(databaseService.getEmployees().toString());
        System.out.println(databaseService.employeeByPinCode("574274"));
//        System.out.println(databaseService.getEmployeeById("789489"));
        System.out.println(databaseService.getEmployees());
//        databaseService.addEmployeePersonalDetails()
    }
}
