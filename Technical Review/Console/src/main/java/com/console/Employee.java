package com.console;

import com.entity.AddressDetails;

import java.io.Serializable;


public class Employee implements Serializable {
    private String employeeFirstName = "";
    private String employeeMobileNumber;
    private AddressDetails permanentAddress;
    private AddressDetails temporaryAddress;
    private String employeeMiddleName = "";
    private String employeeLastName = "";
    private String employeeEmail;
    private String employeeId;

    public Employee(String employeeFirstName, String employeeMobileNumber, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMobileNumber = employeeMobileNumber;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
    }

    public Employee(String employeeFirstName, String employeeMobileNumber, AddressDetails permanentAddress,AddressDetails temporaryAddress, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMobileNumber = employeeMobileNumber;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMobileNumber() {
        return employeeMobileNumber;
    }

    public void setEmployeeMobileNumber(String employeeMobileNumber) {
        this.employeeMobileNumber = employeeMobileNumber;
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

    public Employee() {
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
}
