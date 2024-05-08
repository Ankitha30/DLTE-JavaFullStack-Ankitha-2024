package middleware;

import entity.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entity.AddressDetails;
import entity.PersonalDetails;
import validation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.*;
import java.util.regex.Pattern;

import employee.operations.*;

public class CollectDetails {
    private static Logger logger= LoggerFactory.getLogger(CollectDetails.class);
    private static Scanner scanner =new Scanner(System.in);;
    private static List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
//    List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
    private static  ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static EmployeeActivities empInterface;
//    DatabaseService dataAnalyzer = new DatabaseService();
    //    Matcher matcher

    public static void addEmployeeDetails() {
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
                        logger.info(resourceBundle.getString("re.enter"));
                        System.out.println("Re-enter all the details. ");
                        return;
                    }
                }while(!EmailValidation.isValidEmail(email) );
                System.out.println("Employee Id: ");
                int empId = scanner.nextInt();
                System.out.println("Phone Number");
                String phNo;
                do {

                    phNo = scanner.next();
//
                    if (!PhoneNumberValidation.isValidPhone(phNo)) {
                        logger.warn(resourceBundle.getString("invalid.phone"));

                    }
                    count++;
                    if(count==3) {
                        logger.info(resourceBundle.getString("re.enter"));
//                        System.out.println("Re-enter all the details.Again ");
                        return;
                    }
                } while (!PhoneNumberValidation.isValidPhone(phNo));

                System.out.println(resourceBundle.getString("enter.temporary"));
                System.out.println(resourceBundle.getString("temporary.house"));
                String houseNo = scanner.next();
                scanner.nextLine();
                System.out.println(resourceBundle.getString("temporary.street"));
                Scanner scanner1 = new Scanner(System.in);
                String streetAddress = scanner.nextLine();
                System.out.println(resourceBundle.getString("temporary.city"));
                String city = scanner.nextLine();
//                scanner.nextLine();
                System.out.println(resourceBundle.getString("temporary.state"));
                String state = scanner.next();
                scanner.nextLine();
                System.out.println(resourceBundle.getString("temporary.pin"));
                String pin;
                do {

                    pin = scanner.nextLine();
//
                    if (!PinCodeValidation.isValidPin(pin)) {
                        logger.warn(resourceBundle.getString("invalid.pin"));

                    }
                } while (!PinCodeValidation.isValidPin(pin));

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
                    if (!PinCodeValidation.isValidPin(pPin)) {
                        logger.warn("Invalid Pin.\n Please re-enter the valid Pin.\n");
                    }
                }while (!PinCodeValidation.isValidPin(pPin));

                PersonalDetails personalDetails = new PersonalDetails(name, middleName, lastName, email, phNo, empId);
                AddressDetails addressDetails = new AddressDetails(houseNo,city,streetAddress,state,pin,pHouseNo,pStreet,pCity,pState,pPin, empId);
                EmployeeDetails employeeDetails = new EmployeeDetails(personalDetails, addressDetails); // sending to backend entity
                //System.out.println( em.toString());
//                employeeDetailsList.add(employeeDetails);
//                System.out.println(employeeDetailsList);
                empInterface.addEmployeePersonalDetails(personalDetails);
                empInterface.addAddressDetails(addressDetails);
                System.out.println("Do you want to read one more employee Details(yes/no)");
                yesNo = scanner.next();
                scanner.nextLine();
            }catch (InputMismatchException error){
                logger.error("Input Mismatch occurred while entering "+error.getMessage());
                yesNo="no";
            }
        }while(yesNo.equalsIgnoreCase("yes"));

    }



    public static void displayDetails() {
        try {
//            List<EmployeeDetails> employeeDetails = null;
//            System.out.println(employeeDetails);
//            employeeDetails=dataAnalyzer.getEmployees();
            employeeDetailsList = empInterface.getEmployees();
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

    public static void searchById() throws SQLException {
        System.out.println("Enter the employeeId");
        int id = scanner.nextInt();
        scanner.nextLine();
        EmployeeDetails employee = empInterface.getEmployeeById(id);
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

    public static void searchByPinCode() {
        System.out.println("Enter the pincode");
        String pinCode = scanner.next();
        List<EmployeeDetails> employeeDetails = empInterface.employeeByPinCode(pinCode);
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
