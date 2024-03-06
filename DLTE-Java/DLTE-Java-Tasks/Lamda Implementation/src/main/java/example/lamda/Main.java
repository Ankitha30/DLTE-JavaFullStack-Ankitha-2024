package example.lamda;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main  {
   static Loan loan=new Loan();
    static ArrayList<Loan> loanList= new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        loanList.add(new Loan(1001, 100000.0, new Date("03/04/2023"), "open", "Anu", 9902100234L));
        loanList.add(new Loan(1004, 70000.0, new Date("03/012/2023"), "closed", "Aru", 6902100235L));
        loanList.add(new Loan(1008, 40000.0, new Date("03/01/2023"), "open", "Bhanu", 8902100236L));

        MyBank bank=((start,end) -> {
           for(Loan each:loanList){
               if(each.getLoanDate().after(start) && each.getLoanDate().before(end))
                   System.out.println(each);
           }
        });
        bank.filterDate(new Date("03/03/2023"),new Date("03/20/2023"));
    }
}
