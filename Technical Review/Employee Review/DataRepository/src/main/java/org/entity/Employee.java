package org.entity;

import org.entity.AddressDetails;

import java.io.Serializable;

public class Employee implements Serializable {

    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private String employeeEmail;
    private String employeeMobileNumber;
    private AddressDetails permanentAddress;
    private AddressDetails temporaryAddress;
    private String employeeId;

    public Employee() {
    }

    public Employee(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeMobileNumber, AddressDetails temporaryAddress, AddressDetails permanentAddress, String employeeId) {
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

//    public Employee(String s, String john, Object o, String doe, String s1, String s2, Object o1, Object o2, Object o3) {
//    }

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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
                ", employeeId=" + employeeId +
                '}';
    }
}
