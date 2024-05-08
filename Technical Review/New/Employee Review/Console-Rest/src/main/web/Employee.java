package org.web;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeFirstName = "";
    private String employeeMiddleName = "";
    private String employeeLastName = "";
    private String employeeEmail;
    private String employeeMobileNumber;
    private AddressDetails permanentAddress, temporaryAddress;

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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeMobileNumber='" + employeeMobileNumber + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                '}';
    }
}
