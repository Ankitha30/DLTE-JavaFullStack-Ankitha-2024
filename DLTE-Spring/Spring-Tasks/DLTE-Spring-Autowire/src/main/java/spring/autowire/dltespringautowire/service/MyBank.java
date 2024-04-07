package spring.autowire.dltespringautowire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.autowire.dltespringautowire.model.Loan;
import spring.autowire.dltespringautowire.remote.MyLoanInterface;

import java.util.List;

@Service
public class MyBank {
    @Autowired
    @Qualifier("HomeLoanApplicants")
    private MyLoanInterface myLoanInterface;
    public List<Loan> execute() {
        List<Loan> homeLoanList = myLoanInterface.findAll();
        return homeLoanList;
    }
}


/*
@Autowired:
 This annotation is used by Spring to automatically
  inject the beans by type. It means Spring will look for
  and inject myLoanInterface when the application starts.


@Qualifier("HomeLoanApplicants"):
This annotation is used to resolve the autowiring
conflict when there are multiple beans of the same type.
 In this case, Spring will inject a bean named HomeLoanApplicants
 into myLoanInterface.

 private MyLoanInterface myLoanInterface;:
  This is a declaration of a private field of type MyLoanInterface.
  The myLoanInterface field will be automatically set by Spring
  due to the @Autowired annotation.



 */



/*
@Component
public class MyBank {
    private  LoanImplementation loanImplementation;

    @Autowired
    @Qualifier("homeLoan")
    public void setHomeLoanDependency( LoanImplementation loanImplementation) {
        this.loanImplementation= loanImplementation;
    }

    public List<Loan> callFindAll() {
       return loanImplementation.findALL();
    }
}
 */