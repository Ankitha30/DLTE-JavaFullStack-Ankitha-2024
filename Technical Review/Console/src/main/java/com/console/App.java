package com.console;

import com.entity.AddressDetails;
import com.exception.EmployeeException;
import com.validation.EmailValidation;
import com.validation.*;
import com.validation.PhoneNumberValidation;
import com.validation.PinCodeValidation;
import org.database.DatabaseService;
import org.middleware.Activity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class App {
    private static Logger logger = LoggerFactory.getLogger(EmployeeMenu.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Activity employeeImplement = new DatabaseService();
    Scanner scanner = new Scanner(System.in);
    AddressDetails permanentAddress = new AddressDetails();
    AddressDetails temporaryAddress = new AddressDetails();

    public App() throws SQLException {
    }


//    public App() throws SQLException {
//    }


    public void addEmployeeDetails() {
        String yesNo;
        do {
            try {
                System.out.println("Employee Id: ");
                String empId = null;
//                while(true) {
//                    try {
//                        empId = scanner.nextInt();

//                        if (employeeImplement.doesEmployeeExists(empId))
//                            throw new EmployeeException();
//
//                    } catch (InputMismatchException e) {
//                        logger.info("Not valid format");
//                        System.out.println("Enter valid employee id");
//                    } catch (EmployeeException e) {
//                        System.out.println("Employee already Exists!!");
//                        System.out.println();
//                        return;
//                    }
//
//                }
                int attempts = 0;
//                while(attempts < 3) {
//                    try {
//                        empId = scanner.nextInt();
//                        if (employeeImplement.doesEmployeeExists(empId)) {
//                            throw new EmployeeException();
//                        }
//                        break; // Break out of the loop if everything is fine
//                    } catch (InputMismatchException e) {
//                        attempts++;
//                        logger.info("Not valid format");
//
//                        scanner.nextLine(); // Clear the input buffer
//                    } catch (EmployeeException e) {
//                        logger.info(resourceBundle.getString("emp.exists"));
//                        System.out.println(resourceBundle.getString("emp.exists"));
//                        System.out.println();
//                        return;
//                    }
//                    if(attempts==3){
//                        logger.info("limit exceeded for re-entering the employee id");
//                        System.out.println("Try again later.");
//                        return;
//                    }
//                    System.out.println("Enter valid employee id");
//                }

                while (attempts < 3) {
                    try {
                        empId = scanner.next();
                        if (!IdValidation.isValidId(empId)) {
                            if (employeeImplement.doesEmployeeExists(empId)) {
                                throw new EmployeeException(resourceBundle.getString("emp.exists"));
                            }
                        }
                        break; // Break out of the loop if everything is fine
                    } catch (InputMismatchException e) {
                        attempts++;
                        logger.info("Not valid format");
                        scanner.nextLine(); // Clear the input buffer
                    } catch (EmployeeException e) {
                        logger.info(e.getMessage());
                        System.out.println(e.getMessage());
                        System.out.println();
                        return;
                    }
                    if (attempts == 3) {
                        logger.info("limit exceeded for re-entering the employee id");
                        System.out.println("Try again later.");
                        return;
                    }
                    System.out.println("Enter valid employee id");
                }
                scanner.nextLine();
                System.out.println(resourceBundle.getString("First.name"));
                String name = scanner.nextLine();

                System.out.println(resourceBundle.getString("Middle.name"));
                String middleName = scanner.nextLine();
                System.out.println(resourceBundle.getString("Last.name"));
                String lastName = scanner.nextLine();

                System.out.println(resourceBundle.getString("Email"));
                int count = 0;
                String email;
                while (true) {
                    try {
                        email = scanner.next();
                        if (!EmailValidation.isValidEmail(email)) {
                            if (count == 3) {
                                logger.info("limit exceeded for re-entering the email");
                                System.out.println("Try again later.");
                                return;
                            }
                            logger.warn("Invalid Email.\n Please re-enter the valid email id.\n");
                            throw new EmployeeException(resourceBundle.getString("invalid.email"));
                        } else
                            break;
                    } catch (EmployeeException e) {
                        logger.info(e.getMessage());
                        System.out.println(e.getMessage());

                    }
                    count++;
                }


                String phNo = null;
                System.out.println("Phone Number");
                count = 0;
                while (true) {
                    try {
                        phNo = scanner.next();
                        if (!PhoneNumberValidation.isValidPhone(phNo)) {
                            if (count == 4) {
                                logger.info("limit exceeded for re-entering the phone number");
                                System.out.println("Try again later.");
                                scanner.nextLine();
                                return;
                            }
                            logger.warn("Invalid phone number.\n Please re-enter the valid Phone number.\n");
                            throw new EmployeeException(resourceBundle.getString("invalid.phone"));
                        } else {
                            break;
                        }
                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage());
                    }
                    count++;

                }


                System.out.println("Enter the details of Temporary Address");
                System.out.println("House/Flat Number:");
                String houseNo = scanner.next();
                temporaryAddress.setHouseNumber(houseNo);
                scanner.nextLine();
                System.out.println("Street Address:");
                Scanner scanner1 = new Scanner(System.in);
                String streetAddress = scanner.nextLine();
                temporaryAddress.setStreetAddress(streetAddress);
                System.out.println("City: ");
                String city = scanner.nextLine();
                temporaryAddress.setCityName(city);
//                scanner.nextLine();
                System.out.println("State: ");
                String state = scanner.next();
                temporaryAddress.setState(state);
                scanner.nextLine();
                System.out.println("PIN: ");

                String pin;
                count = 0;
                while (true) {
                    try {
                        pin = scanner.nextLine();
                        if (!PinCodeValidation.isValidPin(pin)) {
                            if (count == 3) {
                                System.out.println("Try again later.");
                                logger.info("attempts limit exceeded");

                                scanner.nextLine();
                                return;
                            }
//                            System.out.println("Invalid PIN.\n Please re-enter the valid PIN.\n");
                            logger.warn("Invalid PIN.\n");
                            throw new EmployeeException(resourceBundle.getString("invalid.pin"));
                        } else
                            break;

                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage());
                    }
                    count++;
                }     //while (!PinCodeValidation.isValidPin(pin));
                temporaryAddress.setAddressPin(pin);
                System.out.println("Enter the the details of Permanent Address");
                System.out.println("House Number:");
                String pHouseNo = scanner.next();
                permanentAddress.setHouseNumber(pHouseNo);
                scanner.nextLine();
                System.out.println("Street: ");
                String pStreet = scanner.nextLine();
                permanentAddress.setStreetAddress(pStreet);
                System.out.println("City: ");
                String pCity = scanner.nextLine();
                permanentAddress.setCityName(pCity);
                System.out.println("State: ");
                String pState = scanner.next();
                permanentAddress.setState(pState);
                scanner.nextLine();
                System.out.println("PIN: ");
                String pPin;
                count = 0;
                while (true) {
                    try {
                        pPin = scanner.nextLine();
                        if (!PinCodeValidation.isValidPin(pPin)) {
                            if (count == 3) {
                                logger.info("limit exceeded for re-entering the pin");
                                System.out.println("Try again later.");

                                scanner.nextLine();
                                return;
                            }
                            logger.warn(resourceBundle.getString("invalid.pin"));
                            System.out.println(resourceBundle.getString("invalid.pin"));
                            throw new EmployeeException(resourceBundle.getString("invalid.pin"));
                        } else
                            break;

                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage());
                    }
                    count++;
                }
                permanentAddress.setAddressPin(pPin);


                Employee employee = new Employee(name, middleName, lastName, email, phNo, empId);
                org.entity.Employee employeeBackend = new org.entity.Employee(employee.getEmployeeFirstName(),
                        employee.getEmployeeMiddleName(),
                        employee.getEmployeeLastName(),
                        employee.getEmployeeEmail(),
                        employee.getEmployeeMobileNumber(),
                        employee.getEmployeeId());
                org.entity.AddressDetails permanentAddressBackend = new org.entity.AddressDetails(
                        permanentAddress.getHouseNumber(),
                        permanentAddress.getCityName(),
                        permanentAddress.getStreetAddress(),
                        permanentAddress.getState(),
                        permanentAddress.getAddressPin());
                org.entity.AddressDetails temporaryAddressBackend = new org.entity.AddressDetails(
                        temporaryAddress.getHouseNumber(),
                        temporaryAddress.getCityName(),
                        temporaryAddress.getStreetAddress(),
                        temporaryAddress.getState(),
                        temporaryAddress.getAddressPin());

                boolean isEmployeeAdded = employeeImplement.addEmployeePersonalDetails(employeeBackend, permanentAddressBackend, temporaryAddressBackend);
//                boolean isPermanentAddressAdded =employeeImplement.addPermanentAddressDetails(permanentAddressBackend);
//                boolean isTemporaryAddressAdded =employeeImplement.addTemporaryAddressDetails(temporaryAddressBackend);
                if (isEmployeeAdded) {
                    System.out.println("Employee details added successfully.");
                } else {
                    System.out.println("Failed to add employee details.");
                }
                System.out.println("Do you want to read one more employee Details(yes/no)");
                yesNo = scanner.next();
                scanner.nextLine();

            } catch (InputMismatchException | org.exceptions.EmployeeException error) {
                logger.error("Input Mismatch occurred while entering " + error.getMessage());
                yesNo = "no";
            }

        } while (yesNo.equalsIgnoreCase("yes"));

    }


    public void displayDetails() {
        try {
            List<org.entity.Employee> frontendDetails = employeeImplement.getEmployees();
            List<Employee> employeeDetails = convertToEmployeeFrontend(frontendDetails);

            if (!employeeDetails.isEmpty()) {
                System.out.println("Employee Details:");
                for (Employee employee : employeeDetails) {
                    System.out.println(resourceBundle.getString("First.name") + employee.getEmployeeFirstName());
                    System.out.println(resourceBundle.getString("Middle.name") + employee.getEmployeeMiddleName());
                    System.out.println(resourceBundle.getString("Last.name") + employee.getEmployeeLastName());
                    System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
                    System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
                    System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());
                    System.out.println();
                    System.out.println(resourceBundle.getString("print.temporary.address"));
                    displayAddressDetails(employee.getTemporaryAddress());
                    System.out.println();
                    System.out.println(resourceBundle.getString("print.permanent.address"));
                    displayAddressDetails(employee.getPermanentAddress());
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                }
            } else
                System.out.println("No employees found");
        }catch(Exception error)
        {
//                System.out.println(error.printStackTrace());
            System.out.println("error in displaying");
        }
    }


    //        display address details
    private void displayAddressDetails(AddressDetails address) {
        System.out.println(resourceBundle.getString("address.house")+address.getHouseNumber());
        System.out.println(resourceBundle.getString("address.street")+address.getStreetAddress());
        System.out.println(resourceBundle.getString("address.city")+address.getCityName());
        System.out.println(resourceBundle.getString("address.state")+address.getState());
        System.out.println(resourceBundle.getString("address.pin")+address.getAddressPin());
        System.out.println();
    }


    // conversion from backend entity to frontend
    private List<Employee> convertToEmployeeFrontend(List<org.entity.Employee> employees) {
        List<Employee> frontendList = new ArrayList<>();
        for (org.entity.Employee employee : employees) {
            Employee frontend = new Employee();
            AddressDetails permanentAddressFrontend = convertToAddressDetailsFrontend(employee.getPermanentAddress());
            AddressDetails temporaryAddressFrontend = convertToAddressDetailsFrontend(employee.getTemporaryAddress());

            frontend.setEmployeeFirstName(employee.getEmployeeFirstName());
            frontend.setEmployeeMiddleName(employee.getEmployeeMiddleName());
            frontend.setEmployeeLastName(employee.getEmployeeLastName());
            frontend.setEmployeeEmail(employee.getEmployeeEmail());
            frontend.setEmployeeMobileNumber(employee.getEmployeeMobileNumber());
            frontend.setEmployeeId(employee.getEmployeeId());
            frontend.setPermanentAddress(permanentAddressFrontend);
            frontend.setTemporaryAddress(temporaryAddressFrontend);

            frontendList.add(frontend);
        }
        return frontendList;
    }

    private AddressDetails convertToAddressDetailsFrontend(org.entity.AddressDetails address) {
        AddressDetails addressFrontend = new AddressDetails();
        addressFrontend.setHouseNumber(address.getHouseNumber());
        addressFrontend.setStreetAddress(address.getStreetAddress());
        addressFrontend.setCityName(address.getCityName());
        addressFrontend.setState(address.getState());
        addressFrontend.setAddressPin(address.getAddressPin());
        return addressFrontend;
    }







    // retrieving employee details based on the given employee id
    public void searchById()  {
        System.out.println("Enter the employeeId");
        String id;
//        scanner.nextInt();
        int count=0;
        while(true) {
            try {
                id = scanner.next();
                if (!PinCodeValidation.isValidPin(id)) {
                    if(count==3){
                        System.out.println("Try again later.");
                        logger.info("attempts limit exceeded");

                        scanner.nextLine();
                        return;
                    }
//                            System.out.println("Invalid PIN.\n Please re-enter the valid PIN.\n");
                    logger.warn("Invalid Employee Id.\n");
                    throw new EmployeeException(resourceBundle.getString("invalid.id"));
                } else
                    break;

            }
            catch (EmployeeException e){
                System.out.println(e.getMessage());
            }
            count++;
        }
        scanner.nextLine();
        try {


            org.entity.Employee employeeBackend = employeeImplement.getEmployeeById(id);
            if (employeeBackend != null) {
                Employee employee = convertToEmployeeFrontendObject(employeeBackend);
                System.out.println(resourceBundle.getString("First.name") + employee.getEmployeeFirstName());
                System.out.println(resourceBundle.getString("Middle.name") + employee.getEmployeeMiddleName());
                System.out.println(resourceBundle.getString("Last.name") + employee.getEmployeeLastName());
                System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
                System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
                System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());
                System.out.println();
                System.out.println(resourceBundle.getString("print.temporary.address"));
                displayAddressDetails(employee.getTemporaryAddress());
                System.out.println();
                System.out.println(resourceBundle.getString("print.permanent.address"));
                displayAddressDetails(employee.getPermanentAddress());
                System.out.println("-------------------------------------------------------------------------------------------------------");
                System.out.println();
            } else
                throw new EmployeeException(resourceBundle.getString("employee.doesNotExists"));
//                System.out.println("Employee does not exists");
        }catch (EmployeeException e){
            System.out.println(e.getMessage());
        }
    }

    private int readEmployeeId(Scanner scanner) throws EmployeeException {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new EmployeeException("Invalid input format. Please enter a valid employee ID (an integer).");
        }
    }
    private Employee convertToEmployeeFrontendObject(org.entity.Employee employee) {
        Employee frontend = new Employee();

        // Set personal details
        frontend.setEmployeeFirstName(employee.getEmployeeFirstName());
        frontend.setEmployeeMiddleName(employee.getEmployeeMiddleName());
        frontend.setEmployeeLastName(employee.getEmployeeLastName());
        frontend.setEmployeeEmail(employee.getEmployeeEmail());
        frontend.setEmployeeMobileNumber(employee.getEmployeeMobileNumber());
        frontend.setEmployeeId(employee.getEmployeeId());

        // Set address details
        AddressDetails temporaryAddress = convertToAddressDetails(employee.getTemporaryAddress());
        AddressDetails permanentAddress = convertToAddressDetails(employee.getPermanentAddress());
        frontend.setTemporaryAddress(new AddressDetails(
                temporaryAddress.getCityName(),
                temporaryAddress.getState(),
                temporaryAddress.getAddressPin(),
                temporaryAddress.getHouseNumber(),
                temporaryAddress.getStreetAddress()

        ));
        frontend.setPermanentAddress(new AddressDetails(
                permanentAddress.getCityName(),
                permanentAddress.getState(),
                permanentAddress.getAddressPin(),
                permanentAddress.getHouseNumber(),
                permanentAddress.getStreetAddress()

        ));

        return frontend;
    }

    private AddressDetails convertToAddressDetails(org.entity.AddressDetails address) {
        AddressDetails addressFrontend = new AddressDetails();
        addressFrontend.setHouseNumber(address.getHouseNumber());
        addressFrontend.setStreetAddress(address.getStreetAddress());
        addressFrontend.setCityName(address.getCityName());
        addressFrontend.setState(address.getState());
        addressFrontend.setAddressPin(address.getAddressPin());
        return addressFrontend;
    }

    public void closeAll(){
        employeeImplement.closeResources();
    }
    public void searchByPinCode() {
        System.out.println(resourceBundle.getString("enter.pincode"));
        String pinCode ;
        int count=0;
        while(true) {
            try {
                pinCode = scanner.nextLine();
                if (!PinCodeValidation.isValidPin(pinCode)) {
                    if(count==3){
                        System.out.println("Try again later.");
                        logger.info("attempts limit exceeded");

                        scanner.nextLine();
                        return;
                    }
//                            System.out.println("Invalid PIN.\n Please re-enter the valid PIN.\n");
                    logger.warn("Invalid PIN.\n");
                    throw new EmployeeException(resourceBundle.getString("invalid.pin"));
                } else
                    break;

            }
            catch (EmployeeException e){
                System.out.println(e.getMessage());
            }
            count++;
        }
        try {
            List<org.entity.Employee> frontendDetails = employeeImplement.employeeByPinCode(pinCode);
            List<Employee> employeeDetails = convertToEmployeeFrontend(frontendDetails);
            if (!employeeDetails.isEmpty()) {
                System.out.println("Details of Employee with  PIN code " + pinCode);
                for (Employee employee : employeeDetails) {
                    System.out.println(resourceBundle.getString("First.name") + employee.getEmployeeFirstName());
                    System.out.println(resourceBundle.getString("Middle.name") + employee.getEmployeeMiddleName());
                    System.out.println(resourceBundle.getString("Last.name") + employee.getEmployeeLastName());
                    System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
                    System.out.println(resourceBundle.getString("Email"));
                    System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());
                    System.out.println();
                    System.out.println(resourceBundle.getString("print.temporary.address"));
                    displayAddressDetails(employee.getTemporaryAddress());
                    System.out.println();
                    System.out.println(resourceBundle.getString("print.permanent.address"));
                    displayAddressDetails(employee.getPermanentAddress());
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------------------------------------------");

                }
            } else
//                System.out.println("Employee with pin code is not found");
                throw new EmployeeException("Employee with PIN code: "+pinCode+" does not exists ");
        }

        catch (EmployeeException e){
            System.out.println(e.getMessage());
        }

    }
}


