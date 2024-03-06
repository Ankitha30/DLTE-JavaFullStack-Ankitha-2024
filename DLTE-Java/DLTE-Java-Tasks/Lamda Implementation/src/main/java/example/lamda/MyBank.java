package example.lamda;

import java.util.ArrayList;
import java.util.Date;

/*

data member: ArrayList of Loan objects: initialize this arraylist with hardcoded Loan objects
abstract method:
Perform filtering  by given range of dates
 */
public interface MyBank {
//    ArrayList<Loan> loanList = new ArrayList<>();
    void filterDate(Date start, Date last);


}
