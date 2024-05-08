package org.database;

import org.entity.*;
import org.exceptions.*;
import org.middleware.Activity;
import org.entity.AddressDetails;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService implements Activity {
    public Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    List<Employee> employees = new ArrayList<Employee>();
    static Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    String employeeId;

    public DatabaseService() throws ConnectionException {
//            connection = HandleDbConnection.databaseConnection();
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("db.error"));
//            System.out.println("Internal Server error.. Contact Administrator");
            System.out.println(e.getMessage());
            throw new ConnectionException(); // Re-throw the exception to indicate failure to the caller
        }
    }

    public boolean addEmployeePersonalDetails(Employee employee, AddressDetails permanent, AddressDetails temporary) throws EmployeeException {
//        employeeId= employee.getEmployeeId();
        employeeId = employee.getEmployeeId();

        if (!EmailValidation.isValidEmail(employee.getEmployeeEmail())) {
            logger.error("Email corrupted");
            throw new EmployeeException("Email changed");
        }
        if (!PhoneNumberValidation.isValidPhone(employee.getEmployeeMobileNumber())) {
            logger.error("Phone number corrupted");
            throw new EmployeeException("Phone Number corrupted");
        }


        try {
            String query = "insert into personal_detail values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeeFirstName());
            preparedStatement.setString(3, employee.getEmployeeMiddleName());
            preparedStatement.setString(4, employee.getEmployeeLastName());
            preparedStatement.setString(5, employee.getEmployeeEmail());
            preparedStatement.setString(6, employee.getEmployeeMobileNumber());
            int rows = preparedStatement.executeUpdate();
            boolean rpermanent = addPermanentAddressDetails(employeeId, permanent);
            boolean rtempp = addTemporaryAddressDetails(employeeId, temporary);

            return rows > 0 & rpermanent & rtempp;
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                logger.warn("integrity constraint ");
                throw new EmployeeException("User already exists");
            }
//            e.printStackTrace();
            return false;
        }

    }

    public boolean addPermanentAddressDetails(String employeeId, AddressDetails addressDetails) throws EmployeeException {
        if (!PinCodeValidation.isValidPin(addressDetails.getAddressPin())) {
            logger.error("Pin code corrupted");
            throw new EmployeeException("Pin code corrupted");
        }
        try {
            String query = "insert into address_details values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, addressDetails.getHouseNumber());
            preparedStatement.setString(3, addressDetails.getStreetAddress());
            preparedStatement.setString(4, addressDetails.getCityName());
            preparedStatement.setString(5, addressDetails.getState());
            preparedStatement.setString(6, addressDetails.getAddressPin());
            preparedStatement.setInt(7, 1);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addTemporaryAddressDetails(String employeeId, AddressDetails addressDetails) throws EmployeeException {
        if (!PinCodeValidation.isValidPin(addressDetails.getAddressPin())) {
            logger.error("Phone number corrupted");
            throw new EmployeeException("Pin code corrupted");
        }

        try {
            String query1 = "insert into address_details values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, addressDetails.getHouseNumber());
            preparedStatement.setString(3, addressDetails.getStreetAddress());
            preparedStatement.setString(4, addressDetails.getCityName());
            preparedStatement.setString(5, addressDetails.getState());
            preparedStatement.setString(6, addressDetails.getAddressPin());
            preparedStatement.setInt(7, 0);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override

    public boolean doesEmployeeExists(String id) {
        String query = "Select employee_id from personal_detail where employee_id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee getEmployeeById(String id) {
        System.out.println(id);
//        try {
//            String query = " select * from personal_detail pd inner join permanent_address_details pa on pd.employee_id=pa.employee_id  inner join temporary_address_details td on td.employee_id=pd.employee_id where pd.employee_id = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, id);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                String employeeId = resultSet.getString(1);
//                String fname = resultSet.getString(2);
//                String mname = resultSet.getString(3);
//                String lname = resultSet.getString(4);
//                String email = resultSet.getString(5);
//                String mobileno = resultSet.getString(6);
//
//                String permanentHouse = resultSet.getString(7);
//                String permanentStreet = resultSet.getString(8);
//                String permanentCity = resultSet.getString(9);
//                String permanentState = resultSet.getString(10);
//                String permanentPin = resultSet.getString(11);
//                String temporaryHouse = resultSet.getString(13);
//                String temporaryStreet = resultSet.getString(14);
//                String temporaryCity = resultSet.getString(15);
//                String temporaryState = resultSet.getString(16);
//                String temporaryPin = resultSet.getString(17);
//                AddressDetails temporaryAddress = new AddressDetails(temporaryHouse,temporaryCity,temporaryStreet,temporaryState,temporaryPin);
//                AddressDetails permanentAddressBackend = new AddressDetails(permanentHouse,permanentCity,permanentStreet,permanentState,permanentPin);
//                Employee employee = new Employee(fname, mname, lname, email, mobileno,temporaryAddress,permanentAddressBackend, employeeId);
//
//                return employee;
//            }
        try {
            String query = "SELECT pd.*, " +
                    "pa.house_number AS permanentHouse, pa.street AS permanentStreet, pa.city AS permanentCity, pa.state AS permanentState, pa.pin AS permanentPin, " +
                    "td.house_number AS temporaryHouse, td.street AS temporaryStreet, td.city AS temporaryCity, td.state AS temporaryState, td.pin AS temporaryPin " +
                    "FROM personal_detail pd " +
                    "INNER JOIN address_details pa ON pd.employee_id = pa.employee_id AND pa.is_permanent = 1 " +
                    "INNER JOIN address_details td ON pd.employee_id = td.employee_id AND td.is_permanent = 0" +
                    "WHERE pd.employee_id = ?\n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String employeeId = resultSet.getString("employee_id");
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);

                String permanentHouse = resultSet.getString("permanentHouse");
                String permanentStreet = resultSet.getString("permanentStreet");
                String permanentCity = resultSet.getString("permanentCity");
                String permanentState = resultSet.getString("permanentState");
                String permanentPin = resultSet.getString("permanentPin");

                String temporaryHouse = resultSet.getString("temporaryHouse");
                String temporaryStreet = resultSet.getString("temporaryStreet");
                String temporaryCity = resultSet.getString("temporaryCity");
                String temporaryState = resultSet.getString("temporaryState");
                String temporaryPin = resultSet.getString("temporaryPin");

                AddressDetails temporaryAddress = new AddressDetails(temporaryHouse, temporaryStreet, temporaryCity, temporaryState, temporaryPin);
                AddressDetails permanentAddress = new AddressDetails(permanentHouse, permanentStreet, permanentCity, permanentState, permanentPin);
                Employee employee = new Employee(fname, mname, lname, email, mobileno, temporaryAddress, permanentAddress, employeeId);

                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Employee> employeeByPinCode(String pin) {
//        try{
//            String query = "select * from personal_detail personal inner join permanent_address_details permanent on pd.employee_id=pa.employee_id  inner join temporary_address_details temporary on temporary.employee_id=personal.employee_id where parmanent.permanent_pin=?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, pin);
//            resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                String employeeId = resultSet.getString(1);
//                String fname = resultSet.getString(2);
//                String mname = resultSet.getString(3);
//                String lname = resultSet.getString(4);
//                String email = resultSet.getString(5);
//                String mobileno = resultSet.getString(6);
//
//                String permanentHouse = resultSet.getString(7);
//                String permanentStreet = resultSet.getString(8);
//                String permanentCity = resultSet.getString(9);
//                String permanentState = resultSet.getString(10);
//                String permanentPin = resultSet.getString(11);
//                String temporaryHouse = resultSet.getString(13);
//                String temporaryStreet = resultSet.getString(14);
//                String temporaryCity = resultSet.getString(15);
//                String temporaryState = resultSet.getString(16);
//                String temporaryPin = resultSet.getString(17);
//               AddressDetails temporaryAddress = new AddressDetails(temporaryHouse,temporaryCity,temporaryStreet,temporaryState,temporaryPin);
//                AddressDetails permanentAddressBackend = new AddressDetails(permanentHouse,permanentCity,permanentStreet,permanentState,permanentPin);
//               Employee employee = new Employee(fname, mname, lname, email, mobileno,temporaryAddress,permanentAddressBackend, employeeId);
//
//////              System.out.println(emp);
//                employees.add(employee);
//                System.out.println(employees);
//
//            }
//            return  employees;
//        }
        try {
            String query = "SELECT personal.*,\n" +
                    "    permanent.house_number  AS permanenthouse,\n" +
                    "    permanent.street AS permanentstreet,\n" +
                    "    permanent.city   AS permanentcity,\n" +
                    "    permanent.state  AS permanentstate,\n" +
                    "    permanent.pin    AS permanentpin,\n" +
                    "    temporary.house_number  AS temporaryHouse,\n" +
                    "    temporary.street AS temporaryStreet,\n" +
                    "    temporary.city   AS temporaryCity,\n" +
                    "    temporary.state  AS temporaryState,\n" +
                    "    temporary.pin    AS temporaryPin \n" +
                    "FROM personal_detail personal \n" +
                    "INNER JOIN address_details permanent ON personal.employee_id = permanent.employee_id AND permanent.is_permanent = 1 \n" +
                    "INNER JOIN address_details temporary ON personal.employee_id = temporary.employee_id AND temporary.is_permanent = 0 \n" +
                    "WHERE permanent.pin = ? or temporary .pin =?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pin);
            preparedStatement.setString(2, pin);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String employeeId = resultSet.getString(1);
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);

                String permanentHouse = resultSet.getString("permanentHouse");
                String permanentStreet = resultSet.getString("permanentStreet");
                String permanentCity = resultSet.getString("permanentCity");
                String permanentState = resultSet.getString("permanentState");
                String permanentPin = resultSet.getString("permanentPin");

                String temporaryHouse = resultSet.getString("temporaryHouse");
                String temporaryStreet = resultSet.getString("temporaryStreet");
                String temporaryCity = resultSet.getString("temporaryCity");
                String temporaryState = resultSet.getString("temporaryState");
                String temporaryPin = resultSet.getString("temporaryPin");

                AddressDetails temporaryAddress = new AddressDetails(temporaryHouse, temporaryStreet, temporaryCity, temporaryState, temporaryPin);
                AddressDetails permanentAddress = new AddressDetails(permanentHouse, permanentStreet, permanentCity, permanentState, permanentPin);
                Employee employee = new Employee(fname, mname, lname, email, mobileno, temporaryAddress, permanentAddress, employeeId);

                employees.add(employee);
//                System.out.println(employees);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
//      try{
//            String query ="select * from personal_detail pd inner join permanent_address_details pa on pd.employee_id=pa.employee_id  inner join temporary_address_details td on td.employee_id=pd.employee_id ";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet=preparedStatement.executeQuery();
//            while (resultSet.next()){
//                String employeeId = resultSet.getString(1);
//                String fname = resultSet.getString(2);
//                String mname = resultSet.getString(3);
//                String lname = resultSet.getString(4);
//                String email = resultSet.getString(5);
//                String mobileno = resultSet.getString(6);
//
//                String permanentHouse = resultSet.getString(7);
//                String permanentStreet = resultSet.getString(8);
//                String permanentCity = resultSet.getString(9);
//                String permanentState = resultSet.getString(10);
//                String permanentPin = resultSet.getString(11);
//                String temporaryHouse = resultSet.getString(13);
//                String temporaryStreet = resultSet.getString(14);
//                String temporaryCity = resultSet.getString(15);
//                String temporaryState = resultSet.getString(16);
//                String temporaryPin = resultSet.getString(17);
//                AddressDetails temporaryAddress = new AddressDetails(temporaryHouse,temporaryCity,temporaryStreet,temporaryState,temporaryPin);
//                AddressDetails permanentAddressBackend = new AddressDetails(permanentHouse,permanentCity,permanentStreet,permanentState,permanentPin);
//               Employee employee = new Employee(fname, mname, lname, email, mobileno,temporaryAddress,permanentAddressBackend, employeeId);
////                EmployeeDetails emp = new EmployeeDetails(personalDetailsBackend, addressDetailsBackend);
//                employees.add(employee);
//
//
//
//          return employees;
        try {
            String query = "SELECT pd.*, " +
                    "pa.house_number AS permanentHouse, pa.street AS permanentStreet, pa.city AS permanentCity, pa.state AS permanentState, pa.pin AS permanentPin, " +
                    "td.house_number AS temporaryHouse, td.street AS temporaryStreet, td.city AS temporaryCity, td.state AS temporaryState, td.pin AS temporaryPin " +
                    "FROM personal_detail pd " +
                    "INNER JOIN address_details pa ON pd.employee_id = pa.employee_id AND pa.is_permanent = 1 " +
                    "INNER JOIN address_details td ON pd.employee_id = td.employee_id AND td.is_permanent = 0";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String employeeId = resultSet.getString(1);
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);

                String permanentHouse = resultSet.getString("permanentHouse");
                String permanentStreet = resultSet.getString("permanentStreet");
                String permanentCity = resultSet.getString("permanentCity");
                String permanentState = resultSet.getString("permanentState");
                String permanentPin = resultSet.getString("permanentPin");

                String temporaryHouse = resultSet.getString("temporaryHouse");
                String temporaryStreet = resultSet.getString("temporaryStreet");
                String temporaryCity = resultSet.getString("temporaryCity");
                String temporaryState = resultSet.getString("temporaryState");
                String temporaryPin = resultSet.getString("temporaryPin");

                AddressDetails temporaryAddress = new AddressDetails(temporaryHouse, temporaryCity, temporaryStreet, temporaryState, temporaryPin);
                AddressDetails permanentAddress = new AddressDetails(permanentHouse, permanentCity, permanentStreet, permanentState, permanentPin);
                Employee employee = new Employee(fname, mname, lname, email, mobileno, temporaryAddress, permanentAddress, employeeId);

                employees.add(employee);
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
