package basics.services;

/*
Encapsulated Class Bonds with maturity, interest rate, their tax status, bondholder, period

Create at least 5 funds in array
sort and view bonds with high rate of interest
 */

public class Bond {
    private Integer maturityAmount;

    public Bond(Integer maturityAmount, Double rateOfInterest, String taxStatus, String bondHolder, Integer tenure) {
        this.maturityAmount = maturityAmount;
        this.rateOfInterest = rateOfInterest;
        this.taxStatus = taxStatus;
        this.bondHolder = bondHolder;
        this.tenure = tenure;
    }

    private Double rateOfInterest;
    private String taxStatus;

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public Integer getTenure() {
        return tenure;
    }

    private String bondHolder;

    public void setMaturityAmount(Integer maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    private Integer tenure;

}
