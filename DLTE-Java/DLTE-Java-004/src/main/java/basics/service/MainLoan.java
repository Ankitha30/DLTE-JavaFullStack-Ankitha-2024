package basics.service;

import java.util.Scanner;
//loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact
public class MainLoan implements MyBank {
    static int totalLoan;
    Loan loan[]=new Loan[10];
    public static void main(String[] args) {
        MainLoan mainLoan=new MainLoan();
        Scanner scanner=new Scanner(System.in);
        while (true) {
        System.out.println("1.Add Loans 2.CheckLoans 3. check closed loans");
        int choice= scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of loans");
                    totalLoan = scanner.nextInt();
                    mainLoan.addNewLoan();
                    break;
                case 2:
                    mainLoan.checkAvailableLoans();
                    break;
                case 3:
                    mainLoan.checkOnlyClosedLoans();
                    break;
                default:
                    scanner.close();
                    System.exit(0);

            }
        }

    }

    @Override
    public void addNewLoan() {
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner=new Scanner(System.in);

        for(int index=0;index<totalLoan;index++) {

            System.out.println("Enter the LoanNumber");
            int loanNumber = scanner.nextInt();
            System.out.println("Enter the Loan Amount");
            double loanAmount = scanner.nextDouble();
            System.out.println("enter the loan Status(open/closed)");
            String loanStatus = scanner1.next();
            System.out.println("Enter the phone number");
            long phoneNumber = scanner.nextLong();
            System.out.println("Enter the Name");
            String borrowerName = scanner1.next();
            System.out.println("Enter the loan date");
            String date=scanner1.next();
            loan[index]=new Loan(loanNumber, loanAmount,date,loanStatus,borrowerName,phoneNumber);
        }

    }

    @Override
    public void checkAvailableLoans() {
        for(int index=0;index<totalLoan;index++){
            if(loan[index]!=null) {
                if (loan[index].getLoanStatus().equalsIgnoreCase("open"))
                    System.out.println(loan[index].toString());
            }
        }

    }

    @Override
    public void checkOnlyClosedLoans() {
            for(int index=0;index<totalLoan;index++) {
                if (loan[index] != null) {
                    if (loan[index].getLoanStatus().equalsIgnoreCase("closed"))
                        System.out.println(loan[index].toString());
                }
            }
    }
}
