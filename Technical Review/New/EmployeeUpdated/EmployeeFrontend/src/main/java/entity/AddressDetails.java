package entity;

public class AddressDetails {
    private String temporaryHouseNumber;
    private String temporaryCityName,temporaryStreetAddress;
    private String temporaryState;
    private String temporaryAddressPin;

    private String permanentHouseNumber;
    private String permanentStreetAddress;
    private String  permanentCityName;
    private String permanentState;
    private String permanentAddressPin;
    private int employeeId;

    public AddressDetails() {
    }

    public AddressDetails(String temporaryHouseNumber, String temporaryCityName, String temporaryStreetAddress, String temporaryState, String temporaryAddressPin,int employeeId,boolean isPermanent){
        this.temporaryHouseNumber = temporaryHouseNumber;
        this.temporaryCityName = temporaryCityName;
        this.temporaryStreetAddress = temporaryStreetAddress;
        this.temporaryState = temporaryState;
        this.temporaryAddressPin = temporaryAddressPin;
    }

    public AddressDetails(String permanentHouseNumber, String permanentStreetAddress, String permanentCityName, String permanentState, String permanentAddressPin, int employeeId){
        this.permanentHouseNumber = permanentHouseNumber;
        this.permanentStreetAddress = permanentStreetAddress;
        this.permanentCityName = permanentCityName;
        this.permanentState = permanentState;
        this.permanentAddressPin = permanentAddressPin;
        this.employeeId = employeeId;

    }

    public AddressDetails(String temporaryHouseNumber, String temporaryCityName, String temporaryStreetAddress, String temporaryState, String temporaryAddressPin, String permanentHouseNumber, String permanentStreetAddress, String permanentCityName, String permanentState, String permanentAddressPin, int employeeId) {
        this.temporaryHouseNumber = temporaryHouseNumber;
        this.temporaryCityName = temporaryCityName;
        this.temporaryStreetAddress = temporaryStreetAddress;
        this.temporaryState = temporaryState;
        this.temporaryAddressPin = temporaryAddressPin;
        this.permanentHouseNumber = permanentHouseNumber;
        this.permanentStreetAddress = permanentStreetAddress;
        this.permanentCityName = permanentCityName;
        this.permanentState = permanentState;
        this.permanentAddressPin = permanentAddressPin;
        this.employeeId = employeeId;
    }

    public String getTemporaryHouseNumber() {
        return temporaryHouseNumber;
    }

    public void setTemporaryHouseNumber(String temporaryHouseNumber) {
        this.temporaryHouseNumber = temporaryHouseNumber;
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

    public String getPermanentStreetAddress() {
        return permanentStreetAddress;
    }

    public void setPermanentStreetAddress(String permanentStreetAddress) {
        this.permanentStreetAddress = permanentStreetAddress;
    }

    public String getPermanentCityName() {
        return permanentCityName;
    }

    public void setPermanentCityName(String permanentCityName) {
        this.permanentCityName = permanentCityName;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentAddressPin() {
        return permanentAddressPin;
    }

    public void setPermanentAddressPin(String permanentAddressPin) {
        this.permanentAddressPin = permanentAddressPin;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "AddressDetails{" +
                "temporaryHouseNumber='" + temporaryHouseNumber + '\'' +
                ", temporaryCityName='" + temporaryCityName + '\'' +
                ", temporaryStreetAddress='" + temporaryStreetAddress + '\'' +
                ", temporaryState='" + temporaryState + '\'' +
                ", temporaryAddressPin='" + temporaryAddressPin + '\'' +
                ", permanentHouseNumber='" + permanentHouseNumber + '\'' +
                ", permanentStreetAddress='" + permanentStreetAddress + '\'' +
                ", permanentCityName='" + permanentCityName + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentAddressPin='" + permanentAddressPin + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
