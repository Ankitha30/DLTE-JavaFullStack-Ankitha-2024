package com.jdbc.springcomponent.entity;



import javax.validation.constraints.*;

public class Employee {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "Numbers and special character are not allowed")
    private String employeeFirstName;
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "Numbers and special character are not allowed")
    @NotNull
    private String employeeMiddleName;
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "Numbers and special character are not allowed")
    private String employeeLastName;
    @Pattern(regexp = " ^(. +)@(\\S+) $", message = "Enter valid email")
    private String employeeEmail;

    public Employee() {
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
                ", employeeId='" + employeeId + '\'' +
                '}';
    }

    @Pattern(regexp = "\\d{10}")
    private String employeeMobileNumber;

    public Employee(@NotNull @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Numbers and special character are not allowed") String employeeFirstName, @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Numbers and special character are not allowed") @NotNull String employeeMiddleName, @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Numbers and special character are not allowed") String employeeLastName, @Pattern(regexp = " ^(. +)@(\\S+) $", message = "Enter valid email") String employeeEmail, @Pattern(regexp = "\\d{10}") String employeeMobileNumber, AddressDetails permanentAddress, AddressDetails temporaryAddress, @Pattern(regexp = "\\d", message = "employee Id should be in number") String employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeMobileNumber = employeeMobileNumber;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.employeeId = employeeId;
    }

    private AddressDetails permanentAddress;
    private AddressDetails temporaryAddress;
    @Pattern(regexp = "\\d",message = "employee Id should be in number")
    private String employeeId;

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
}
