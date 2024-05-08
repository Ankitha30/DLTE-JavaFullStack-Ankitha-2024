package org.middleware;

import java.io.Serializable;

public class PersonalDetails implements Serializable {
    private String employeeFirstName="",employeeMiddleName="",employeeLastName="";
    private String  employeeEmail;
    private String employeeMobileNumber;

    public PersonalDetails() {
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeMobileNumber=" + employeeMobileNumber +
                ", employeeId=" + employeeId +"\n"+
                '}';
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public PersonalDetails(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeEmail, String employeeMobileNumber, int employeeId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeMobileNumber = employeeMobileNumber;
        this.employeeId = employeeId;
    }

    private int employeeId;

}
