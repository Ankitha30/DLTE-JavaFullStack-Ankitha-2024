package basics.service;

import java.util.Scanner;

public class Transaction {
    public static void main(String[] args) {
        double initialAmount=2000,newAmount=0.0;
        int debitCount=0;
      Scanner s = new Scanner(System.in);
        for(int index=0;index<10;index++)
        {
            System.out.println("Enter the new Amount");
            newAmount=s.nextDouble();
            if(initialAmount>newAmount)
                debitCount++;
            initialAmount=newAmount;
        }

        System.out.println("Total Debit Count: "+debitCount);

    }
}
