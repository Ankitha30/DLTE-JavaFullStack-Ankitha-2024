package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        MyBank myBankMethods=new MyBankMethods();
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.Add Loans 2.CheckLoans 3. check closed loans 4.Exit");
            int choice= scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter the LoanNumber");
                    int loanNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the Loan Amount");
                    double loanAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("enter the loan Status(open/closed)");
                    String loanStatus = scanner.next();
                    scanner.nextLine();
                    System.out.println("Enter the phone number");
                    long phoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Enter the Name");
                    String borrowerName = scanner.next();
                    scanner.nextLine();
                    System.out.println("Enter the loan date");
                    String date=scanner.next();
                    scanner.nextLine();
                    Loan loanData = new Loan(loanNumber, loanAmount, date, loanStatus, borrowerName, phoneNumber);
                    try{
                        myBankMethods.addNewLoan(loanData);
                        System.out.println("Loan Added");
                    } catch (IOException  | ClassNotFoundException e) {
                        System.out.println("Couldn't add loan "+e.getMessage());
                    }
<<<<<<< HEAD

=======
>>>>>>> 00aeca281833340279cc9bb617362a8f77a59221
                    break;
                case 2:
                    System.out.println("Opened Loans");
                    myBankMethods.checkAvailableLoans();
                    break;
                case 3:
                    System.out.println("Closed Loans");
                    myBankMethods.checkClosedLoans();
                    break;
                default:
                    scanner.close();
                    System.exit(0);


            }
        }
    }
}
