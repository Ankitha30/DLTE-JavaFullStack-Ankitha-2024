package com.console.springboot;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.*;



//@ComponentScan("com.jdbc.springcomponent.services.EmployeeService")
@Service
public class CollectAndDisplayDetails{

    //    @Autowired
//    EmployeeRepo employeeRepo;
    public static String url = "http://localhost:9000/employee";
    private RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(EmployeeMenu.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    Activity employeeImplement = new DatabaseService();

    Scanner scanner = new Scanner(System.in);
    AddressDetails permanentAddress = new AddressDetails();
    AddressDetails temporaryAddress = new AddressDetails();





    public void displayDetails() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url + "/view/");
//        sendHttpGetRequest(httpClient,url+"/view/");
        CloseableHttpResponse response = httpClient.execute
        (httpGet);
//        Gson gson = new Gson();
        String jsonResponse = EntityUtils.toString(response.getEntity());
        Type list = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        List<Employee> employeeList = new Gson().fromJson(jsonResponse, list);
        System.out.println(employeeList);
        System.out.println();
        System.out.println();
        System.out.println("Employee Details:");
        for (Employee employee : employeeList) {
            System.out.println(resourceBundle.getString("Name") + employee.getEmployeeFirstName() + " "+employee.getEmployeeMiddleName()+" " + employee.getEmployeeLastName());
            System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
            System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
            System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());

            // Print permanent address details
            AddressDetails permanentAddress = employee.getPermanentAddress();
            if (permanentAddress != null) {
                System.out.println(resourceBundle.getString("permanent.address"));
                System.out.println(resourceBundle.getString("address.house") + permanentAddress.getHouseNumber());
                System.out.println(resourceBundle.getString("address.city") + permanentAddress.getCityName());
                System.out.println(resourceBundle.getString("address.street")+ permanentAddress.getStreetAddress());
                System.out.println(resourceBundle.getString("address.state") + permanentAddress.getState());
                System.out.println(resourceBundle.getString("address.pin") + permanentAddress.getAddressPin());
            }

            // Print temporary address details
            AddressDetails temporaryAddress = employee.getTemporaryAddress();
            if (temporaryAddress != null) {
                System.out.println("Temporary Address:");
                System.out.println(resourceBundle.getString("address.house") + temporaryAddress.getHouseNumber());
                System.out.println(resourceBundle.getString("address.city")  + temporaryAddress.getCityName());
                System.out.println(resourceBundle.getString("address.street")+ temporaryAddress.getStreetAddress());
                System.out.println(resourceBundle.getString("address.state") + temporaryAddress.getState());
                System.out.println(resourceBundle.getString("address.pin") + temporaryAddress.getAddressPin());
            }

            System.out.println("-------------------------------------------------------------------------------"); // Empty line for separation between employees
        }

        // Close the HTTP client and release resources
        httpClient.close();
    }

    public void searchByPinCode() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("enter the pin");
        String pin = scanner.next();
        int count=0;
        while(true) {
            try {
                pin = scanner.nextLine();
                if (!Validation.isValidPin(pin)) {
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
            HttpGet httpGet = new HttpGet(url + "/pincode/" + pin);
//        sendHttpGetRequest(httpClient,url+"/view/");
            CloseableHttpResponse response = httpClient.execute
                    (httpGet);
//        Gson gson = new Gson();
            String jsonResponse = EntityUtils.toString(response.getEntity());
            Type list = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            List<Employee> employeeList = new Gson().fromJson(jsonResponse, list);
            System.out.println(employeeList);
            System.out.println();
            System.out.println();
            System.out.println("Employee Details:");
            if(!employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(resourceBundle.getString("Name") + employee.getEmployeeFirstName() + " " + employee.getEmployeeMiddleName() + " " + employee.getEmployeeLastName());
                    System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
                    System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
                    System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());

                    // Print permanent address details
                    AddressDetails permanentAddress = employee.getPermanentAddress();
                    if (permanentAddress != null) {
                        System.out.println("Permanent Address:");
                        System.out.println(resourceBundle.getString("address.house")  + permanentAddress.getHouseNumber());
                        System.out.println(resourceBundle.getString("address.city") + permanentAddress.getCityName());
                        System.out.println(resourceBundle.getString("address.street")+ permanentAddress.getStreetAddress());
                        System.out.println(resourceBundle.getString("address.state")  + permanentAddress.getState());
                        System.out.println(resourceBundle.getString("address.pin") + permanentAddress.getAddressPin());
                    }

                    // Print temporary address details
                    AddressDetails temporaryAddress = employee.getTemporaryAddress();
                    if (temporaryAddress != null) {
                        System.out.println("Temporary Address:");
                        System.out.println(resourceBundle.getString("address.house")  + temporaryAddress.getHouseNumber());
                        System.out.println(resourceBundle.getString("address.city") + temporaryAddress.getCityName());
                        System.out.println(resourceBundle.getString("address.street") + temporaryAddress.getStreetAddress());
                        System.out.println(resourceBundle.getString("address.state") + temporaryAddress.getState());
                        System.out.println(resourceBundle.getString("address.pin") + temporaryAddress.getAddressPin());
                    }

                    System.out.println("-------------------------------------------------------------------------------"); // Empty line for separation between employees
                }
            }else
//                System.out.println("Employee with pin code is not found");
                throw new EmployeeException("Employee with PIN code: "+pin+" does not exists ");

        } catch (EmployeeException e){
            System.out.println(e.getMessage());
        }finally {

            httpClient.close();
        }
    }

    public void searchById() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("enter the id");
        String pin = scanner.next();
        HttpGet httpGet = new HttpGet(url + "/id/"+pin);
//        sendHttpGetRequest(httpClient,url+"/view/");
        CloseableHttpResponse response = httpClient.execute(httpGet);
//        Gson gson = new Gson();
        String jsonResponse = EntityUtils.toString(response.getEntity());
//        Type list = new TypeToken<ArrayList<Employee>>() {
//        }.getType();
        Employee employee = new Gson().fromJson(jsonResponse, Employee.class);
        System.out.println(employee);


        System.out.println(resourceBundle.getString("Name") + employee.getEmployeeFirstName() + " " + employee.getEmployeeMiddleName() + " " + employee.getEmployeeLastName());
        System.out.println(resourceBundle.getString("Employee.ID") + employee.getEmployeeId());
        System.out.println(resourceBundle.getString("Email") + employee.getEmployeeEmail());
        System.out.println(resourceBundle.getString("phone.number") + employee.getEmployeeMobileNumber());


        // Printing permanent address details
        AddressDetails permanentAddress = employee.getPermanentAddress();
        if (permanentAddress != null) {
            System.out.println("Permanent Address:");
            System.out.println(resourceBundle.getString("address.house")  + permanentAddress.getHouseNumber());
            System.out.println(resourceBundle.getString("address.city")+ permanentAddress.getCityName());
            System.out.println(resourceBundle.getString("address.street") + permanentAddress.getStreetAddress());
            System.out.println(resourceBundle.getString("address.state")  + permanentAddress.getState());
            System.out.println(resourceBundle.getString("address.pin") + permanentAddress.getAddressPin());
        }

        // Printing temporary address details
        AddressDetails temporaryAddress = employee.getTemporaryAddress();
        if (temporaryAddress != null) {
            System.out.println("Temporary Address:");
            System.out.println(resourceBundle.getString("address.house")  + temporaryAddress.getHouseNumber());
            System.out.println(resourceBundle.getString("address.city")+ temporaryAddress.getCityName());
            System.out.println(resourceBundle.getString("address.street") + temporaryAddress.getStreetAddress());
            System.out.println(resourceBundle.getString("address.pin") + temporaryAddress.getAddressPin());
        }
    }


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

                        break; // Break out of the loop if everything is fine
                    } catch (InputMismatchException e) {
                        attempts++;
                        logger.info("Not valid format");
                        scanner.nextLine(); // Clear the input buffer
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
//                        if (!Validation.isValidEmail(email)) {
//                            if (count == 3) {
//                                logger.info("limit exceeded for re-entering the email");
//                                System.out.println("Try again later.");
//                                return;
//                            }
//                            logger.warn("Invalid Email.\n Please re-enter the valid email id.\n");
//                            throw new EmployeeException(resourceBundle.getString("invalid.email"));
//                        } else
                            break;
                    }
                    catch (Exception e) {
                        logger.info(e.getMessage());
                        System.out.println(e.getMessage());

                    }
//                    catch (EmployeeException e) {
//                        logger.info(e.getMessage());
//                        System.out.println(e.getMessage());
//
//                    }
                    count++;
                }



                String phNo = null;
                System.out.println("Phone Number");
                count = 0;
                while (true) {
                    try {
                        phNo = scanner.next();
                        if (!Validation.isValidPhone(phNo)) {
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
                        if (!Validation.isValidPin(pin)) {
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
                        if (!Validation.isValidPin(pPin)) {
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
                Employee employee = new Employee(name, middleName, lastName, email, phNo, permanentAddress, temporaryAddress, empId);
                CloseableHttpClient httpclient = HttpClients.createDefault();// This method returns a new CloseableHttpClient instance configured with default settings. This client will be used to execute HTTP requests (e.g., POST, GET) to a server.
                HttpPost httppost = new HttpPost(url + "/input/");
                Gson gson=new Gson();
                String jsonData = gson.toJson(employee);
                System.out.println(jsonData);
                StringEntity entity=new StringEntity(jsonData);
                httppost.setEntity(entity);
                httppost.setHeader("Content-Type", "application/json");
                try(CloseableHttpResponse response = httpclient.execute(httppost)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        System.out.println("Employee details submitted successfully!");
                    } else {
                        System.out.println("Failed to submit employee details.");

                    }
                }catch (IOException e) {
                    System.err.println(e.getMessage());
                }


                System.out.println("Do you want to read one more employee Details(yes/no)");
                yesNo = scanner.next();
                scanner.nextLine();

            } catch (InputMismatchException | IOException error) {
                logger.error("Input Mismatch occurred while entering " + error.getMessage());
                yesNo = "no";
            }

        } while (yesNo.equalsIgnoreCase("yes"));

    }








}

