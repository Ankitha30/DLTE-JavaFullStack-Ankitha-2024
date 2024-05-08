package backend.middleware;

import java.io.Serializable;

public class AddressDetails implements Serializable {
    private String  permanentCityName;
    private String temporaryState;
    private String temporaryAddressPin;
    private String permanentHouseNumber;
    private String permanentState;
    private String permanentStreetAddress;
    private String temporaryCityName,temporaryStreetAddress;
    private String permanentAddressPin;
    private String temporaryHouseNumber;
    private int employeeId;

    @Override
    public String toString() {
        return "AddressDetails{" +
                "permanentCityName='" + permanentCityName + '\'' +
                ", temporaryState='" + temporaryState + '\'' +
                ", temporaryAddressPin='" + temporaryAddressPin + '\'' +
                ", permanentHouseNumber='" + permanentHouseNumber + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentStreetAddress='" + permanentStreetAddress + '\'' +
                ", temporaryCityName='" + temporaryCityName + '\'' +
                ", temporaryStreetAddress='" + temporaryStreetAddress + '\'' +
                ", permanentAddressPin='" + permanentAddressPin + '\'' +
                ", temporaryHouseNumber='" + temporaryHouseNumber + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }

    public AddressDetails(String permanentCityName, String temporaryState, String temporaryAddressPin, String permanentHouseNumber, String permanentState, String permanentStreetAddress, String temporaryCityName, String temporaryStreetAddress, String permanentAddressPin, String temporaryHouseNumber, int employeeId) {
        this.permanentCityName = permanentCityName;
        this.temporaryState = temporaryState;
        this.temporaryAddressPin = temporaryAddressPin;
        this.permanentHouseNumber = permanentHouseNumber;
        this.permanentState = permanentState;
        this.permanentStreetAddress = permanentStreetAddress;
        this.temporaryCityName = temporaryCityName;
        this.temporaryStreetAddress = temporaryStreetAddress;
        this.permanentAddressPin = permanentAddressPin;
        this.temporaryHouseNumber = temporaryHouseNumber;
        this.employeeId = employeeId;
    }



    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPermanentCityName() {
        return permanentCityName;
    }

    public void setPermanentCityName(String permanentCityName) {
        this.permanentCityName = permanentCityName;
    }

    public AddressDetails() {
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public String getTemporaryAddressPin() {
        return temporaryAddressPin;
    }

    public void setTemporaryAddressPin(String temporaryAddressPin) {
        this.temporaryAddressPin = temporaryAddressPin;
    }

    public String getPermanentHouseNumber() {
        return permanentHouseNumber;
    }

    public void setPermanentHouseNumber(String permanentHouseNumber) {
        this.permanentHouseNumber = permanentHouseNumber;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentStreetAddress() {
        return permanentStreetAddress;
    }

    public void setPermanentStreetAddress(String permanentStreetAddress) {
        this.permanentStreetAddress = permanentStreetAddress;
    }

    public String getTemporaryCityName() {
        return temporaryCityName;
    }

    public void setTemporaryCityName(String temporaryCityName) {
        this.temporaryCityName = temporaryCityName;
    }

    public String getTemporaryStreetAddress() {
        return temporaryStreetAddress;
    }

    public void setTemporaryStreetAddress(String temporaryStreetAddress) {
        this.temporaryStreetAddress = temporaryStreetAddress;
    }

    public String getPermanentAddressPin() {
        return permanentAddressPin;
    }

    public void setPermanentAddressPin(String permanentAddressPin) {
        this.permanentAddressPin = permanentAddressPin;
    }

    public String getTemporaryHouseNumber() {
        return temporaryHouseNumber;
    }

    public void setTemporaryHouseNumber(String temporaryHouseNumber) {
        this.temporaryHouseNumber = temporaryHouseNumber;
    }


}
