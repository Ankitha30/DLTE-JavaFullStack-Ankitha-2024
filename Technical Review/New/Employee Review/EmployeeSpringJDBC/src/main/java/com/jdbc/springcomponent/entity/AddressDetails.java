package com.jdbc.springcomponent.entity;

public class AddressDetails {
    private String houseNumber;
    private String cityName;
    private String streetAddress;
    private String state;
    private String addressPin;

    public String getHouseNumber() {
        return houseNumber;
    }

    public AddressDetails() {
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public AddressDetails(String houseNumber, String cityName, String streetAddress, String state, String addressPin) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.state = state;
        this.addressPin = addressPin;
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
