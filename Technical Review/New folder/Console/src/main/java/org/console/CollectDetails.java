package org.console;

import org.database.DatabaseService;
import org.middleware.AddressDetails;
import org.middleware.EmployeeDetails;
import org.middleware.PersonalDetails;
import org.database.DatabaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

import java.util.regex.Pattern;

public class CollectDetails {
    private static Logger logger= LoggerFactory.getLogger(CollectAndDisplayDetails.class);
    List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    DatabaseService dataAnalyzer = new DatabaseService();
//    Matcher matcher
    Scanner scanner = new Scanner(System.in);
    public void addEmployeeDetails() {
        String yesNo;
        do{
            try {


                System.out.println(resourceBundle.getString("First.name"));
                String name = scanner.nextLine();
                System.out.println(resourceBundle.getString("Middle.name"));
                String middleName = scanner.nextLine();
                System.out.println(resourceBundle.getString("Last.name"));
                String lastName = scanner.nextLine();
                System.out.println("Email:");
                int count =0;
                String email ;
                do{
                    email= scanner.next();
                    count++;
                    if(count==3) {
                        System.out.println("Re-enter all the details. ");
                        return;
                    }
                }while(!isValidEmail(email) );
                System.out.println("Employee Id: ");
                int empId = scanner.nextInt();
                System.out.println("Phone Number");
                String phNo;
                do {

                    phNo = scanner.next();
//
                    if (!phNo.matches("\\d{10}")) {
                        logger.warn("Invalid phone number.\n Please re-enter the valid Phone number.\n");

                    }
                    count++;
                    if(count==3) {
                        System.out.println("Re-enter all the details.Again ");
                        return;
                    }
                } while (!phNo.matches("\\d{10}"));

                System.out.println("Enter the details of Temporary Address");
                System.out.println("House/Flat Number:");
                String houseNo = scanner.next();
                scanner.nextLine();
                System.out.println("Street Address:");
                Scanner scanner1 = new Scanner(System.in);
                String streetAddress = scanner.nextLine();
                System.out.println("City: ");
                String city = scanner.nextLine();
//                scanner.nextLine();
                System.out.println("State: ");
                String state = scanner.next();
                scanner.nextLine();
                System.out.println("PIN: ");
                String pin;
                do {

                    pin = scanner.nextLine();
//
                    if (!pin.matches("\\d{6}")) {
                        logger.warn("Invalid PIN.\n Please re-enter the valid PIN.\n");

                    }
                } while (!pin.matches("\\d{6}"));

//                scanner.nextLine();e
//        employeeAddressDetail[i]=new EmployeeAddressDetails(city, streetAddress, flatNum, state, pin);

                System.out.println("Enter the the details of Permanent Address");
                System.out.println("House Number:");
                String pHouseNo = scanner.next();
                scanner.nextLine();
                System.out.println("Street: ");
                String pStreet = scanner.nextLine();
                System.out.println("City: ");
                String pCity = scanner.nextLine();
                System.out.println("State: ");
                String pState = scanner.next();
                scanner.nextLine();
                System.out.println("PIN: ");
                String pPin ;
                do {
                    pPin = scanner.nextLine();
                    if (!pPin.matches("\\d{6}")) {
                        logger.warn("Invalid Pin.\n Please re-enter the valid Pin.\n");
                    }
                } while (!pPin.matches("\\d{6}"));

                PersonalDetails personalDetails = new PersonalDetails(name, middleName, lastName, email, phNo, empId);
                AddressDetails addressDetails = new AddressDetails(pCity, state, pin, pHouseNo, pState, pStreet, city, streetAddress, pPin, houseNo,empId);
                EmployeeDetails employeeDetails = new EmployeeDetails(personalDetails, addressDetails);
                //System.out.println( em.toString());
                employeeDetailsList.add(employeeDetails);
//                System.out.println(employeeDetailsList);
                dataAnalyzer.addEmployeePersonalDetails(personalDetails);
                dataAnalyzer.addAddressDetails(addressDetails);
                System.out.println("Do you want to read one more employee Details(yes/no)");
                yesNo = scanner.next();
                scanner.nextLine();
            }catch (InputMismatchException error){
                logger.error("Input Mismatch occurred while entering "+error.getMessage());
                yesNo="no";
            }
        }while(yesNo.equalsIgnoreCase("yes"));

    }

    private boolean isValidEmail(String email) {
        String emailRegex= "^[a-zA-Z0-9._]+@[a-zA-z0-9._]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(email).matches())
            return true;
        else {
            logger.warn("Invalid Email");
            System.out.println("Enter the valid email");
        }
        return false;
    }

    public void displayDetails() {
        try {
//            List<EmployeeDetails> employeeDetails = null;
//            System.out.println(employeeDetails);
//            employeeDetails=dataAnalyzer.getEmployees();
             employeeDetailsList = dataAnalyzer.getEmployees();
            System.out.println(employeeDetailsList);
            if (!employeeDetailsList.isEmpty()) {
                System.out.println("Employee Details");
                for (EmployeeDetails employee : employeeDetailsList) {
                    System.out.println(resourceBundle.getString("First.name")+employee.getPersonalDetails().getEmployeeFirstName());
                    System.out.println(resourceBundle.getString("Middle.name") +employee.getPersonalDetails().getEmployeeMiddleName());
                    System.out.println(resourceBundle.getString("Last.name") +employee.getPersonalDetails().getEmployeeLastName());
                    System.out.println(resourceBundle.getString("Employee.ID")+employee.getPersonalDetails().getEmployeeId());
                    System.out.println(resourceBundle.getString("Email")+employee.getPersonalDetails().getEmployeeEmail());
                    System.out.println(resourceBundle.getString("phone.number")+employee.getPersonalDetails().getEmployeeMobileNumber());
                    System.out.println();
                    System.out.println("Temporary Address Details");
                    System.out.println(resourceBundle.getString("temporary.house")+employee.getAddressDetails().getTemporaryHouseNumber());
                    System.out.println(resourceBundle.getString("temporary.street")+employee.getAddressDetails().getTemporaryStreetAddress());
                    System.out.println(resourceBundle.getString("temporary.city")+employee.getAddressDetails().getTemporaryCityName());
                    System.out.println(resourceBundle.getString("temporary.state")+employee.getAddressDetails().getTemporaryState());
                    System.out.println(resourceBundle.getString("temporary.pin")+employee.getAddressDetails().getTemporaryAddressPin());
                    System.out.println();
                    System.out.println("Permanent Address Details");
                    System.out.println(resourceBundle.getString("permanent.house")+employee.getAddressDetails().getPermanentHouseNumber());
                    System.out.println(resourceBundle.getString("permanent.street")+employee.getAddressDetails().getPermanentStreetAddress());
                    System.out.println(resourceBundle.getString("permanent.city")+employee.getAddressDetails().getPermanentCityName());
                    System.out.println(resourceBundle.getString("permanent.state")+employee.getAddressDetails().getPermanentState());
                    System.out.println(resourceBundle.getString("permanent.pin")+employee.getAddressDetails().getPermanentAddressPin());
                    System.out.println("-------------------------------------------------------------------------------------------------------");

                }
            } else
                System.out.println("No employees found");
        }catch (Exception error)
        {
            System.out.println("error in displaying");
        }


    }

    public void searchById() throws SQLException {
        System.out.println("Enter the employeeId");
        int id = scanner.nextInt();
        scanner.nextLine();
        EmployeeDetails employee = dataAnalyzer.getEmployeeById(id);
        if(employee !=null) {
            System.out.println(resourceBundle.getString("First.name")+employee.getPersonalDetails().getEmployeeFirstName());
            System.out.println(resourceBundle.getString("Middle.name") +employee.getPersonalDetails().getEmployeeMiddleName());
            System.out.println(resourceBundle.getString("Last.name") +employee.getPersonalDetails().getEmployeeLastName());
            System.out.println(resourceBundle.getString("Employee.ID")+employee.getPersonalDetails().getEmployeeId());
            System.out.println(resourceBundle.getString("Email")+employee.getPersonalDetails().getEmployeeEmail());
            System.out.println(resourceBundle.getString("phone.number")+employee.getPersonalDetails().getEmployeeMobileNumber());
            System.out.println();
            System.out.println("Temporary Address Details");
            System.out.println(resourceBundle.getString("temporary.house")+employee.getAddressDetails().getTemporaryHouseNumber());
            System.out.println(resourceBundle.getString("temporary.street")+employee.getAddressDetails().getTemporaryStreetAddress());
            System.out.println(resourceBundle.getString("temporary.city")+employee.getAddressDetails().getTemporaryCityName());
            System.out.println(resourceBundle.getString("temporary.state")+employee.getAddressDetails().getTemporaryState());
            System.out.println(resourceBundle.getString("temporary.pin")+employee.getAddressDetails().getTemporaryAddressPin());
            System.out.println();
            System.out.println("Permanent Address Details");
            System.out.println(resourceBundle.getString("permanent.house")+employee.getAddressDetails().getPermanentHouseNumber());
            System.out.println(resourceBundle.getString("permanent.street")+employee.getAddressDetails().getPermanentStreetAddress());
            System.out.println(resourceBundle.getString("permanent.city")+employee.getAddressDetails().getPermanentCityName());
            System.out.println(resourceBundle.getString("permanent.state")+employee.getAddressDetails().getPermanentState());
            System.out.println(resourceBundle.getString("permanent.pin")+employee.getAddressDetails().getPermanentAddressPin());
            System.out.println("-------------------------------------------------------------------------------------------------------");

        }
        else
            System.out.println("employee not found");
    }

    public void searchByPinCode() {
        System.out.println("Enter the pincode");
        String pinCode = scanner.next();
        List<EmployeeDetails> employeeDetails = dataAnalyzer.employeeByPinCode(pinCode);
        if(!employeeDetails.isEmpty()){
            System.out.println("Employee with following pincode");
            for(EmployeeDetails employee: employeeDetails) {
                System.out.println("Details of Employee with Employee pincode "+ pinCode);
                System.out.println(resourceBundle.getString("First.name")+employee.getPersonalDetails().getEmployeeFirstName());
                System.out.println(resourceBundle.getString("Middle.name") +employee.getPersonalDetails().getEmployeeMiddleName());
                System.out.println(resourceBundle.getString("Last.name") +employee.getPersonalDetails().getEmployeeLastName());
                System.out.println(resourceBundle.getString("Employee.ID")+employee.getPersonalDetails().getEmployeeId());
                System.out.println(resourceBundle.getString("Email")+employee.getPersonalDetails().getEmployeeEmail());
                System.out.println(resourceBundle.getString("phone.number")+employee.getPersonalDetails().getEmployeeMobileNumber());
                System.out.println();
                System.out.println("Temporary Address Details");
                System.out.println(resourceBundle.getString("temporary.house")+employee.getAddressDetails().getTemporaryHouseNumber());
                System.out.println(resourceBundle.getString("temporary.street")+employee.getAddressDetails().getTemporaryStreetAddress());
                System.out.println(resourceBundle.getString("temporary.city")+employee.getAddressDetails().getTemporaryCityName());
                System.out.println(resourceBundle.getString("temporary.state")+employee.getAddressDetails().getTemporaryState());
                System.out.println(resourceBundle.getString("temporary.pin")+employee.getAddressDetails().getTemporaryAddressPin());
                System.out.println();
                System.out.println("Permanent Address Details");
                System.out.println(resourceBundle.getString("permanent.house")+employee.getAddressDetails().getPermanentHouseNumber());
                System.out.println(resourceBundle.getString("permanent.street")+employee.getAddressDetails().getPermanentStreetAddress());
                System.out.println(resourceBundle.getString("permanent.city")+employee.getAddressDetails().getPermanentCityName());
                System.out.println(resourceBundle.getString("permanent.state")+employee.getAddressDetails().getPermanentState());
                System.out.println(resourceBundle.getString("permanent.pin")+employee.getAddressDetails().getPermanentAddressPin());
                System.out.println("-------------------------------------------------------------------------------------------------------");

            }
        }
        else
            System.out.println("Employee with pin code is not found");

    }
}
