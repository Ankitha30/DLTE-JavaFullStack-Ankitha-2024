package basics.service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GPay gPay=new GPay("Ankitha", 123456789L, 23000D, 123456789L, "1290", 123, "Ankitha");

        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        while(true) {
            System.out.println("1. Withdraw  2. PayBills 3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:

                    System.out.println("Enter the amount to withdrawn");
                    double withdrawAmount = scanner.nextDouble();
                    gPay.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println("Enter the biller name");
                    String billerName = scanner1.nextLine();
                    System.out.println("Enter the  biller type");
                    String billType = scanner1.nextLine();


                    System.out.println("Enter the bill amount ");
                    Double billAmount = scanner1.nextDouble();

                    gPay.payBills(billerName, billAmount, billType);
                    break;
                default:scanner.close();
                        scanner1.close();
                        System.exit(0);
            }
        }


    }

}
