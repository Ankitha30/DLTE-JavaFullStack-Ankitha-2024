package com.console;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.validation.EmailValidation;

import com.validation.PhoneNumberValidation;
import com.validation.PinCodeValidation;
import exception.EmployeeException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.database.DatabaseService;
import org.middleware.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.IdValidation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.*;

public class App {
    private static Logger logger = LoggerFactory.getLogger(EmployeeMenu.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Activity employeeImplement = new DatabaseService();
    Scanner scanner = new Scanner(System.in);
    AddressDetails permanentAddress = new AddressDetails();
    AddressDetails temporaryAddress = new AddressDetails();
    public static String url = "http://localhost:7001/EmployeeRest";
//    public static String url = resourceBundle.getString("url.header");

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
                int attempts = 0;


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


                Employee employee1 = new Employee(name, middleName, lastName, email, phNo, empId);
                Employee employee = new Employee(name, middleName, lastName, email, phNo, permanentAddress, temporaryAddress, empId);

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
                org.entity.Employee employeeBackend = new org.entity.Employee(employee.getEmployeeFirstName(),
                        employee.getEmployeeMiddleName(),
                        employee.getEmployeeLastName(),
                        employee.getEmployeeEmail(),
                        employee.getEmployeeMobileNumber(),
                        permanentAddressBackend,
                        temporaryAddressBackend,
                        employee.getEmployeeId());

                writeEmployee(employeeBackend);
//                boolean isEmployeeAdded = employeeImplement.addEmployeePersonalDetails(employeeBackend, permanentAddressBackend, temporaryAddressBackend);
//                boolean isPermanentAddressAdded = employeeImplement.addPermanentAddressDetails(permanentAddressBackend);
//                boolean isTemporaryAddressAdded = employeeImplement.addTemporaryAddressDetails(temporaryAddressBackend);
//                if (isEmployeeAdded ) {
//                    System.out.println("Employee details added successfully.");
//                } else {
//                    System.out.println("Failed to add employee details.");
//                }
                System.out.println("Do you want to read one more employee Details(yes/no)");
                yesNo = scanner.next();
                scanner.nextLine();

            } catch (InputMismatchException | IOException error) {
                logger.error("Input Mismatch occurred while entering " + error.getMessage());
                yesNo = "no";
            }

        } while (yesNo.equalsIgnoreCase("yes"));

    }


    //        display address details
    private void displayAddressDetails(AddressDetails address) {
        System.out.println(resourceBundle.getString("address.house") + address.getHouseNumber());
        System.out.println(resourceBundle.getString("address.street") + address.getStreetAddress());
        System.out.println(resourceBundle.getString("address.city") + address.getCityName());
        System.out.println(resourceBundle.getString("address.state") + address.getState());
        System.out.println(resourceBundle.getString("address.pin") + address.getAddressPin());
        System.out.println();
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


    public void searchByPinCode() {
        System.out.println(resourceBundle.getString("enter.pincode"));
        String pinCode;
        int count = 0;
        while (true) {
            try {
                pinCode = scanner.nextLine();
                if (!PinCodeValidation.isValidPin(pinCode)) {
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
                throw new EmployeeException("Employee with PIN code: " + pinCode + " does not exists ");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

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


    private int readEmployeeId(Scanner scanner) throws EmployeeException {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new EmployeeException("Invalid input format. Please enter a valid employee ID (an integer).");
        }
    }

    public void searchById() {
        System.out.println("Enter the employeeId");
        String id;
        int count = 0;
        while (true) {
            try {
                id = scanner.next();
                if (!IdValidation.isValidId(id)) {
                    if (count == 3) {
                        System.out.println("Try again later.");
                        logger.info("attempts limit exceeded");
                        scanner.nextLine();
                        return;
                    }
                    logger.warn("Invalid Employee Id.\n");
                    throw new EmployeeException(resourceBundle.getString("invalid.id"));
                } else {
                    break;
                }
            } catch (EmployeeException e) {
                System.out.println(e.getMessage());
            }
            count++;
        }
        scanner.nextLine();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url + "/id/" + id);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    Gson gson = new Gson();
                    String jsonResponse = EntityUtils.toString(response.getEntity());
                    System.out.println("JSON response: " + jsonResponse); // Debug logging
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(jsonResponse);
                    if (element.isJsonObject()) {
                        Employee employee = gson.fromJson(element, Employee.class);
                        System.out.println("Employee details retrieved successfully:");
                        System.out.println("First Name: " + employee.getEmployeeFirstName());
                        // Output other employee details similarly
                    } else {
                        System.out.println("Invalid JSON format received from server.");
                    }
                } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                    System.out.println("Employee with ID " + id + " does not exist.");
                } else {
                    System.out.println("Failed to fetch employee details. Status code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void searchById() {
//        System.out.println("Enter the employeeId");
//        String id;
////        scanner.nextInt();
//        int count = 0;
//        while (true) {
//            try {
//                id = scanner.next();
//                if (!IdValidation.isValidId(id)) {
//                    if (count == 3) {
//                        System.out.println("Try again later.");
//                        logger.info("attempts limit exceeded");
//
//                        scanner.nextLine();
//                        return;
//                    }
////                            System.out.println("Invalid PIN.\n Please re-enter the valid PIN.\n");
//                    logger.warn("Invalid Employee Id.\n");
//                    throw new EmployeeException(resourceBundle.getString("invalid.id"));
//                } else
//                    break;
//
//            } catch (EmployeeException e) {
//                System.out.println(e.getMessage());
//            }
//            count++;
//        }
//        scanner.nextLine();
//        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpGet httpGet = new HttpGet(url+"/id/"+id);
//            try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                int statusCode = response.getStatusLine().getStatusCode();
//                if(statusCode==HttpStatus.SC_OK){
//                    Gson gson = new Gson();
//                    String jsonResponse = EntityUtils.toString(response.getEntity());
//                    JsonParser parser=new JsonParser();
//                    JsonElement element= parser.parse(jsonResponse);
//                    if(element.isJsonObject()){
//
//                        org.entity.Employee employeeBackend = employeeImplement.getEmployeeById(id);
//                        if (employeeBackend != null) {
//                            Employee employee = gson.fromJson(element, Employee.class);
//                             employee = convertToEmployeeFrontendObject(employeeBackend);
//                            System.out.println(resourceBundle.getString("First.name") + employee.getEmployeeFirstName());
//                            System.out.println(resourceBundle.getString("Middle.name") + employee.getEmployeeMiddleName());
//                            System.out.println(resourceBundle.getString("Last.name") + employee.getEmployeeLastName());
//                            System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
//                            System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
//                            System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());
//                            System.out.println();
//                            System.out.println(resourceBundle.getString("print.temporary.address"));
//                            displayAddressDetails(employee.getTemporaryAddress());
//                            System.out.println();
//                            System.out.println(resourceBundle.getString("print.permanent.address"));
//                            displayAddressDetails(employee.getPermanentAddress());
//                            System.out.println("-------------------------------------------------------------------------------------------------------");
//                            System.out.println();
//
//                    }
//                        else
//                            throw new EmployeeException(resourceBundle.getString("employee.doesNotExists"));
////
//                }
//                    else
//                        logger.info("json object not found");
//            }else
//                logger.info("failed to fetch element");
//
//
//            }
//            System.out.println("Employee does not exists");
//        } catch (EmployeeException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void closeAll() {
        employeeImplement.closeResources();
    }


    public void writeEmployee(org.entity.Employee employee) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url + "/add/");
        Gson gson = new Gson();
        String writeData = gson.toJson(employee);
        StringEntity entity = new StringEntity(writeData);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpclient.execute(httppost);
    }

    public void displayDetails() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            System.out.println(httpclient);
            HttpGet httpget = new HttpGet(url + "/display/");
            System.out.println(httpget);
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                System.out.println("response:" + response);
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("get entity " + response.getEntity());
                if (statusCode == HttpStatus.SC_OK) {
                    Gson gson = new Gson();
                    String jsonResponse = EntityUtils.toString(response.getEntity());
                    System.out.println("json response: " + jsonResponse);
                    JsonParser parser = new JsonParser();
                    System.out.println(parser);
                    JsonElement element = parser.parse(jsonResponse);
                    System.out.println(element);
                    if (element.isJsonArray()) {
                        Type listType = new TypeToken<ArrayList<Employee>>() {
                        }.getType();
                        List<Employee> employeeDetails = gson.fromJson(element, listType);

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
                        } else {
                            System.out.println("No employees found");
                        }
                    } else {
                        System.out.println("Unexpected JSON structure");
                    }
                } else {
                    System.out.println("HTTP Request failed with status code: " + statusCode);
                }
            } catch (IOException e) {
                System.out.println("Error occurred while processing the HTTP response");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while establishing HTTP connection");
        }
    }

}


// retrieving employee details based on the given employee id












































/*
private static EmployeeConsole translateBack(Employee employee) {

   EmployeeBasicDetailsConsole employeeBasicDetailsConsole=new EmployeeBasicDetailsConsole();
   EmployeeAddressConsole tempAddress=new EmployeeAddressConsole();
   EmployeeAddressConsole permAddress=new EmployeeAddressConsole();
   employeeBasicDetailsConsole.setFirstName(employee.getEmployeeBasicDetails().getFirstName());
   employeeBasicDetailsConsole.setMiddleName(employee.getEmployeeBasicDetails().getMiddleName());
   employeeBasicDetailsConsole.setLastName(employee.getEmployeeBasicDetails().getLastName());
   employeeBasicDetailsConsole.setEmployeeID(employee.getEmployeeBasicDetails().getEmployeeID());
   employeeBasicDetailsConsole.setEmailID(employee.getEmployeeBasicDetails().getEmailID());
   employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

   tempAddress.setHouseName(employee.getTemporaryEmployeeAddress().getHouseName());
   tempAddress.setHouseStreet(employee.getTemporaryEmployeeAddress().getHouseName());
   tempAddress.setCityName(employee.getTemporaryEmployeeAddress().getCityName());
   tempAddress.setStateName(employee.getTemporaryEmployeeAddress().getStateName());
   tempAddress.setPinCode(employee.getTemporaryEmployeeAddress().getPinCode());

   permAddress.setHouseName(employee.getPermanentEmployeeAddress().getHouseName());
   permAddress.setHouseStreet(employee.getPermanentEmployeeAddress().getHouseStreet());
   permAddress.setCityName(employee.getPermanentEmployeeAddress().getCityName());
   permAddress.setStateName(employee.getPermanentEmployeeAddress().getStateName());
   permAddress.setPinCode(employee.getPermanentEmployeeAddress().getPinCode());
   return new EmployeeConsole(employeeBasicDetailsConsole,tempAddress,permAddress);
}
 */


//    private boolean isValidEmail(String email) {
//        String emailRegex= "^[a-zA-Z0-9._]+@[a-zA-z0-9._]+\\.[a-zA-Z]{2,4}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        if(pattern.matcher(email).matches())
//            return true;
//        else {
//            logger.warn("Invalid Email");
//            System.out.println("Enter the valid email");
//        }
//        return false;
//    }


//                System.out.println(resourceBundle.getString("First.name")+employee.getPersonalDetails().getEmployeeFirstName());
//                System.out.println(resourceBundle.getString("Middle.name") +employee.getPersonalDetails().getEmployeeMiddleName());
//                System.out.println(resourceBundle.getString("Last.name") +employee.getPersonalDetails().getEmployeeLastName());
//                System.out.println(resourceBundle.getString("Employee.ID")+employee.getPersonalDetails().getEmployeeId());
//                System.out.println(resourceBundle.getString("Email")+employee.getPersonalDetails().getEmployeeEmail());
//                System.out.println(resourceBundle.getString("phone.number")+employee.getPersonalDetails().getEmployeeMobileNumber());
//                System.out.println();
//                System.out.println("Temporary Address Details");
//                System.out.println(resourceBundle.getString("temporary.house")+employee.getAddressDetails().getTemporaryHouseNumber());
//                System.out.println(resourceBundle.getString("temporary.street")+employee.getAddressDetails().getTemporaryStreetAddress());
//                System.out.println(resourceBundle.getString("temporary.city")+employee.getAddressDetails().getTemporaryCityName());
//                System.out.println(resourceBundle.getString("temporary.state")+employee.getAddressDetails().getTemporaryState());
//                System.out.println(resourceBundle.getString("temporary.pin")+employee.getAddressDetails().getTemporaryAddressPin());
//                System.out.println();
//                System.out.println("Permanent Address Details");
//                System.out.println(resourceBundle.getString("permanent.house")+employee.getAddressDetails().getPermanentHouseNumber());
//                System.out.println(resourceBundle.getString("permanent.street")+employee.getAddressDetails().getPermanentStreetAddress());
//                System.out.println(resourceBundle.getString("permanent.city")+employee.getAddressDetails().getPermanentCityName());
//                System.out.println(resourceBundle.getString("permanent.state")+employee.getAddressDetails().getPermanentState());
//                System.out.println(resourceBundle.getString("permanent.pin")+employee.getAddressDetails().getPermanentAddressPin());
//                System.out.println("-------------------------------------------------------------------------------------------------------");


//            List<Employee> employeeDetails = new ArrayList<>();
//            System.out.println(employeeDetails);
////            employeeDetails=dataAnalyzer.getEmployees();
//            employeeDetails =employeeImplement.getEmployees();
//            System.out.println(employeeDetailsList);
//            if (!employeeDetailsList.isEmpty()) {
//                System.out.println("Employee Details");
//                for (Employee employee : employeeDetails) {
//                    System.out.println(resourceBundle.getString("First.name")+employee.getEmployeeFirstName());
//                    System.out.println(resourceBundle.getString("Middle.name") +employee.getEmployeeMiddleName());
//                    System.out.println(resourceBundle.getString("Last.name") +employee.getEmployeeLastName());
//                    System.out.println(resourceBundle.getString("Employee.ID")+employee.getEmployeeId());
//                    System.out.println(resourceBundle.getString("Email")+employee.getEmployeeEmail());
//                    System.out.println(resourceBundle.getString("phone.number")+employee.getEmployeeMobileNumber());
//                    System.out.println();
//                    System.out.println("Temporary Address Details");
//                    System.out.println();
//                    System.out.println("-------------------------------------------------------------------------------------------------------");
