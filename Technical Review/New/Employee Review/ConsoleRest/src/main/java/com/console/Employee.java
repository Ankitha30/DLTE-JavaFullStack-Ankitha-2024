package com.console;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeFirstName = "";
    private String employeeMobileNumber;
    private AddressDetails permanentAddress, temporaryAddress;
    private String employeeMiddleName = "";
    private String employeeLastName = "";
    private String employeeEmail;

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeMobileNumber='" + employeeMobileNumber + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                ", employeeId=" + employeeId +
                '}';
    }


    public AddressDetails getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressDetails permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressDetails getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(AddressDetails temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public Employee(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeMobileNumber, AddressDetails permanentAddress, AddressDetails temporaryAddress, String employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeMobileNumber = employeeMobileNumber;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.employeeId = employeeId;
    }

    public Employee(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeMobileNumber, String employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeMobileNumber = employeeMobileNumber;
        this.employeeId = employeeId;
    }


    public Employee() {
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeMobileNumber() {
        return employeeMobileNumber;
    }

    public void setEmployeeMobileNumber(String employeeMobileNumber) {
        this.employeeMobileNumber = employeeMobileNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

//    public PersonalDetails(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeMobileNumber, int employeeId) {
//        this.employeeFirstName = employeeFirstName;
//        this.employeeMiddleName = employeeMiddleName;
//        this.employeeLastName = employeeLastName;
//        this.employeeEmail = employeeEmail;
//        this.employeeMobileNumber = employeeMobileNumber;
//        this.employeeId = employeeId;
//    }

    private String employeeId;

}
