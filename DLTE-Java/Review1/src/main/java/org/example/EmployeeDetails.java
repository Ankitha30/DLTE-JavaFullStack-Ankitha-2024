package org.example;

import java.util.Scanner;

public class EmployeeDetails extends Employee {
    public void CollectDetails(Employee employee){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name");
        employee.setEmployeeFirstName(scanner.next());
//        System.out.println("Enter your Middle name (required)");
//        employeeMiddleName = scanner.nextLine();
//        System.out.println("Enter your last name (required)");
//        employeeLastName = scanner.nextLine();
//        System.out.println("Enter your email address");
//        employeeEmail = scanner.next();
//        System.out.println("Enter your Employee Id");
//        employeeId = scanner.nextInt();
//        System.out.println("Enter your mobile number");
//        employeeMobileNumber = scanner.nextLong();
//        System.out.println("Enter the details of your Temporary/Current address");
//        System.out.print("House Number: ");
//        temporaryHouseNumber=scanner.nextLine();
//        System.out.print("\nStreet: ");
//        Scanner scanner1 = new Scanner(System.in);
//        temporaryStreetAddress = scanner1.nextLine();
//        System.out.print("\nCity: ");
//
//
//        System.out.println("Enter your Permanent Address ");
//        permanentAddress=scanner1.nextLine();


    }
//    public static void main(String[] args) {


//        scanner.close();
//        scanner1.close();
//        System.out.println();
//        System.out.println("----Employee Details---");
//        System.out.println("Name: "+employeeFirstName+" "+employeeMiddleName+' '+employeeLastName);
//        System.out.println("Email: "+employeeEmail);
//        System.out.println("Employee Id: "+employeeId);
//        System.out.println("Phone Number: "+employeeMobileNumber);
//        System.out.println("Temporary Address: "+temporaryAddress);
//        System.out.println("Permanent Address: "+permanentAddress);



    }
}
