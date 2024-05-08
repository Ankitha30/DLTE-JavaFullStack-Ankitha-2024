package org.database;

import oracle.jdbc.driver.OracleDriver;
import org.middleware.Activity;
import org.middleware.AddressDetails;
import org.middleware.EmployeeDetails;
import org.middleware.PersonalDetails;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseService implements Activity {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement1;
    private ResultSet resultSet;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("databse");
    List<EmployeeDetails>  employees;
    AddressDetails addressDetails;
    PersonalDetails personalDetails;
    EmployeeDetails employeeDetails;
    int employeeId;
    public DatabaseService(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void addEmployeePersonalDetails(PersonalDetails personalDetails)
    {
        employeeId=personalDetails.getEmployeeId();
        //employee_id number primary key,firts_name varchar(255),middle_name varchar(255),last_name varchar(255),email varchar(255),mobile_number number);
        try{
            String query="insert into personal_details values(?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,personalDetails.getEmployeeId());
            preparedStatement.setString(2,personalDetails.getEmployeeFirstName());
            preparedStatement.setString(3,personalDetails.getEmployeeMiddleName());
            preparedStatement.setString(4,personalDetails.getEmployeeLastName());
            preparedStatement.setString(5,personalDetails.getEmployeeEmail());
            preparedStatement.setString(6,personalDetails.getEmployeeMobileNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addAddressDetails(AddressDetails addressDetails){
        try{
            String query = "insert into permanent_address_details values(?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,addressDetails.getPermanentHouseNumber());
            preparedStatement.setString(2,addressDetails.getPermanentStreetAddress());
            preparedStatement.setString(3,addressDetails.getPermanentCityName());
            preparedStatement.setString(4,addressDetails.getPermanentState());
            preparedStatement.setString(5,addressDetails.getPermanentAddressPin());
            preparedStatement.setInt(6,employeeId);
            preparedStatement.executeUpdate();
            String query1="insert into temporary_address_details values(?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(query1);
            preparedStatement.setString(1,addressDetails.getTemporaryHouseNumber());
            preparedStatement.setString(2,addressDetails.getTemporaryStreetAddress());
            preparedStatement.setString(3,addressDetails.getTemporaryCityName());
            preparedStatement.setString(4,addressDetails.getTemporaryState());
            preparedStatement.setString(5,addressDetails.getTemporaryAddressPin());
            preparedStatement.setInt(6,employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EmployeeDetails getEmployeeById(int id)  {
        System.out.println(id);
        try {
            String query = " select * from personal_details pd inner join permanent_address_details pa on pd.employee_id=pa.employee_id  inner join temporary_address_details td on td.employee_id=pd.employee_id where pd.employee_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int employeeId = resultSet.getInt(1);
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);
                String phouse = resultSet.getString(7);
                String pstreet = resultSet.getString(8);
                String pcity = resultSet.getString(9);
                String pstate = resultSet.getString(10);
                String ppin = resultSet.getString(11);
                String thouse = resultSet.getString(13);
                String tstreet = resultSet.getString(14);
                String tcity = resultSet.getString(15);
                String tstate = resultSet.getString(16);
                String tpin = resultSet.getString(17);
                PersonalDetails personalDetails = new PersonalDetails(fname, mname, lname, email, mobileno, employeeId);
                AddressDetails addressDetails = new AddressDetails(pcity, tstate, tpin, phouse, pstate, pstreet, tcity, tstreet, ppin, thouse, employeeId);
                EmployeeDetails emp = new EmployeeDetails(personalDetails, addressDetails);
                return emp;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

return  null;

    }

    @Override
    public List<EmployeeDetails> employeeByPinCode(String pin) {
        employees=new ArrayList<>();
        try{
            String query = "select * from personal_details pd inner join permanent_address_details pa on pd.employee_id=pa.employee_id  inner join temporary_address_details td on td.employee_id=pd.employee_id where pa.permanent_pin=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(pin));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Results found");
                int employeeId = resultSet.getInt(1);
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);
                String phouse = resultSet.getString(7);
                String pstreet = resultSet.getString(8);
                String pcity = resultSet.getString(9);
                String pstate = resultSet.getString(10);
                String ppin = resultSet.getString(11);
                String thouse = resultSet.getString(13);
                String tstreet = resultSet.getString(14);
                String tcity = resultSet.getString(15);
                String tstate = resultSet.getString(16);
                String tpin = resultSet.getString(17);
                PersonalDetails personalDetails = new PersonalDetails(fname, mname, lname, email, mobileno, employeeId);
                AddressDetails addressDetails = new AddressDetails(pcity, tstate, tpin, phouse, pstate, pstreet, tcity, tstreet, ppin, thouse, employeeId);
                EmployeeDetails emp = new EmployeeDetails(personalDetails, addressDetails);
//                System.out.println(emp);
                employees.add(emp);
//                System.out.println(employees);

            }
            return  employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeDetails> getEmployees() {
        employees=new ArrayList<>();

      try{
            String query ="select * from personal_details pd inner join permanent_address_details pa on pd.employee_id=pa.employee_id  inner join temporary_address_details td on td.employee_id=pd.employee_id ";
            preparedStatement = connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int employeeId = resultSet.getInt(1);
                String fname = resultSet.getString(2);
                String mname = resultSet.getString(3);
                String lname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String mobileno = resultSet.getString(6);
                String phouse = resultSet.getString(7);
                String pstreet = resultSet.getString(8);
                String pcity = resultSet.getString(9);
                String pstate = resultSet.getString(10);
                String ppin = resultSet.getString(11);
                String thouse = resultSet.getString(13);
                String tstreet = resultSet.getString(14);
                String tcity = resultSet.getString(15);
                String tstate = resultSet.getString(16);
                String tpin = resultSet.getString(17);
                PersonalDetails personalDetails = new PersonalDetails(fname, mname, lname, email, mobileno, employeeId);
                AddressDetails addressDetails = new AddressDetails(pcity, tstate, tpin, phouse, pstate, pstreet, tcity, tstreet, ppin, thouse, employeeId);
                EmployeeDetails emp = new EmployeeDetails(personalDetails, addressDetails);
                employees.add(emp);
//                personalDetails.setEmployeeId(resultSet.getInt(1));
//                personalDetails.setEmployeeFirstName(resultSet.getString(2));
//                personalDetails.setEmployeeMiddleName(resultSet.getString(3));
//                personalDetails.setEmployeeLastName(resultSet.getString(4));
//                personalDetails.setEmployeeEmail(resultSet.getString(5));
//                personalDetails.setEmployeeMobileNumber(resultSet.getString(6));
//                addressDetails.setPermanentHouseNumber(resultSet.getString(7));
//                addressDetails.setPermanentStreetAddress(resultSet.getString(8));
//                addressDetails.setPermanentCityName(resultSet.getString(9));
//                addressDetails.setPermanentState(resultSet.getString(10));
//                addressDetails.setPermanentAddressPin(resultSet.getString(11));
//                addressDetails.setEmployeeId(resultSet.getInt(12));
//                addressDetails.setTemporaryHouseNumber(resultSet.getString(13));
//                addressDetails.setTemporaryStreetAddress(resultSet.getString(14));
//                addressDetails.setTemporaryCityName(resultSet.getString(15));
//                addressDetails.setTemporaryState(resultSet.getString(16));
//                addressDetails.setTemporaryAddressPin(resultSet.getString(17));
//                addressDetails.setEmployeeId(resultSet.getInt(12));
//                employeeDetails = new EmployeeDetails(personalDetails,addressDetails);
//                System.out.println(employeeDetails);
//                employees.add(employeeDetails);

            }
          return employees;
      }catch (SQLException e) {
          e.printStackTrace();
      }
        return null;
    }

}
