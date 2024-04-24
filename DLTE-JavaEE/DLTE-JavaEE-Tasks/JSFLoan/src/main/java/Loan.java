import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.Date;

@ManagedBean(name="loan")
@ApplicationScoped
public class Loan {
    private long loanNumber;
    private double loanAmount;
    private String loanDate;
    private String loanStatus, borrowerName;
    private Long borrowerContact;

    public Loan() {

    }


//    @PostConstruct
//    public void initializeMyLoan(){
//        loanNumber = 8767890;
//        loanAmount = 20000.0;
//        loanDate= new Date("03/04/2023");
//        loanStatus="closed";
//        borrowerName="Anu";
//        borrowerContact=9902311234L;
//    }



    public Long getLoanNumber() {
        return loanNumber;
    }

    public Loan(long loanNumber, double loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                '}';
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }


}


//    Build Java EE project with JSF for performing CRUD operations of LOAN entity using Collections as data storage
//
//        Loan: loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;
//
//        UI with Binding perform following
//
//        add new loan
//        display only closed loan
//        delete loan