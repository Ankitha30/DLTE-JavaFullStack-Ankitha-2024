package spring.rest.controller.springbootcontroller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
Build Rest Controller with List of Loan object

Perform following:

GetMapping: index as pathvariable and display loan in that specified index

PostMapping: new loan into the List
 */


@RestController
@RequestMapping("/loans")
public class LoanController {
     List<Loan> loanList = new ArrayList<>();


    public LoanController() {
        loanList.add(new Loan(2340009L, 50000,"open","Anu"));
        loanList.add(new Loan(4528009L,100000,"closed","Suni"));
        loanList.add(new Loan(3781900L,38900, "open", "Sanju"));
        loanList.add(new Loan(6738920L,75000,"open","Manu"));
    }

    @GetMapping("/{index}")
    public  Loan getLoan(@PathVariable("index") int index){
        return loanList.get(index);
    }

    @GetMapping("/view")
    public List<Loan> getLoanList(){return loanList.subList(0,loanList.size());}

    @PostMapping("/")
    public String addLoan(@RequestBody Loan loan){
        loanList.add(loan);
        return loan.getBorrowerName()+" added successfully";
    }
}
