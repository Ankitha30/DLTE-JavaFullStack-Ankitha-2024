import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ApplicationScoped
public class LoanService {

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    List<Loan> loanList = new ArrayList<>();


    public LoanService() {
        loanList.add(new Loan(78345690L, 2000,"3/7/2024",  "closed", "Anu", 9034234564L));
        loanList.add(new Loan(78367230L, 5000, "3/7/2024", "open", "Aru", 9034234564L));
        loanList.add(new Loan(78342324L, 3000,"3/7/2024",  "closed", "Anusha", 9034234564L));
    }
    public List<Loan> addNewLoan(Loan loan){
        loanList.add(loan);
        return loanList;
    }
    public List<Loan> displayClosedLoans(){
        List<Loan> filteredList = loanList.stream().filter(loan->loan.getLoanStatus().equalsIgnoreCase("closed")).collect(Collectors.toList());
        return  filteredList;
    }
    public void deleteLoan(Long loanNum){
        System.out.println(loanNum);
        loanList.removeIf(loan -> loan.getLoanNumber()==loanNum);


    }

}
