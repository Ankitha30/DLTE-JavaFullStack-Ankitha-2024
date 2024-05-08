package org.entity;

import java.io.Serializable;

public class AddressDetails implements Serializable {
    private String houseNumber;
    private String cityName;
    private String streetAddress;
    private String state;
    private String addressPin;
    private String empId;

    @Override
    public String toString() {
        return "AddressDetails{" +
                "houseNumber='" + houseNumber + '\'' +
                ", cityName='" + cityName + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", state='" + state + '\'' +
                ", addressPin='" + addressPin + '\'' +
                ", empId='" + empId + '\'' +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public AddressDetails(String houseNumber, String cityName, String streetAddress, String state, String addressPin, String empId) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.state = state;
        this.addressPin = addressPin;
        this.empId = empId;
    }

    public AddressDetails(String houseNumber, String cityName, String streetAddress, String state, String addressPin) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.state = state;
        this.addressPin = addressPin;

    }

    public AddressDetails() {
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressPin() {
        return addressPin;
    }

    public void setAddressPin(String addressPin) {
        this.addressPin = addressPin;
    }


}
