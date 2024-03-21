package spring.autowire.dltespringautowire;

import org.springframework.stereotype.Component;
import spring.autowire.dltespringautowire.model.Loan;
import spring.autowire.dltespringautowire.remote.MyLoanInterface;

import java.util.ArrayList;
import java.util.List;

@Component("PersonalLoanApplicants")
public class PersonalLoanImplementation implements MyLoanInterface {
    @Override
    public List<Loan> findAll() {
        List<Loan> personalLoanList = new ArrayList<>();
        for(Loan loan:myLoan){
            if(loan.getLoanType().equalsIgnoreCase("personal loan"))
//                System.out.println(loan.getBorrowerName()+" "+loan.getLoanNumber()+" "+loan.getLoanAmount()+" "+loan.getLoanType()+" "+loan.getLoanStatus());
                personalLoanList.add(loan);
        }
        return personalLoanList;
    }
}
