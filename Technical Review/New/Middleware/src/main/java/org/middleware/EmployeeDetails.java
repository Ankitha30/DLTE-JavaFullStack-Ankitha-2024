package org.middleware;

import java.io.Serializable;

public class EmployeeDetails implements Serializable {
    public EmployeeDetails(PersonalDetails personalDetails, AddressDetails addressDetails) {
        this.personalDetails = personalDetails;
        this.addressDetails = addressDetails;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "personalDetails=" + personalDetails +
                ", addressDetails=" + addressDetails +"\n"+
                '}';
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }

    private PersonalDetails personalDetails;
    private AddressDetails addressDetails;
}
