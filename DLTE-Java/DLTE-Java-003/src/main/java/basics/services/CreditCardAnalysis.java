package basics.services;

import java.util.Date;
import java.util.Scanner;

public class CreditCardAnalysis {
    public static void main(String[] args) {
        CreditCard[] myBank={
                new CreditCard(8765678765678L,"Ankitha",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(2343234345445L,"Divija",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                new CreditCard(8765678764545L,"Eeksha",new Date(2031,5,15),955,400000,new Date(2024,3,05),new Date(2024,03,5),9864),
                new CreditCard(1234565456767L,"Akshatha",new Date(2028,8,11),767,700000,new Date(2024,3,18),new Date(2024,03,5),1945),
        };
        CreditCardAnalysis analysis=new CreditCardAnalysis();
        System.out.println("---------WelCome--------------");
        System.out.println();
        while(true) {
            System.out.println("---------Menu--------");
            System.out.println("1. Filter based on the given limit   2.Filter based on the date of bill payment  \n3.Update specific PIN of customer    4. Update the limit of customers those date of bill generation is 05th    \n5. Exit");
            System.out.println("Choose the option");
            int choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the start limit");
                    int startLimit = scanner.nextInt();
                    System.out.println("Enter the endLimit");
                    int endLimit = scanner.nextInt();
                    CustomerSupport support=new CustomerSupport();
                    analysis.filterLimit(myBank,startLimit,endLimit);
                    break;
                case 2:
                    System.out.println("Enter the start Date(enter only date)");
                    int start = scanner.nextInt();
                    System.out.println("Enter the to date");
                    int end = scanner.nextInt();
                    analysis.filterBillPayments(myBank,start,end);
                    break;
                case 3:
                    System.out.println("Enter the credit card number");
                    Long cardNumber = scanner.nextLong();
                    System.out.println("Enter the old PIN");
                    Integer oldPin = scanner.nextInt();
                    Integer newPin;
                    for(CreditCard each: myBank) {
                        if (oldPin.equals(each.getCreditCardPin())) {
                            System.out.println("Enter the new PIN");
                            newPin =  scanner.nextInt();
                            each.setCreditCardPin(newPin);
                            System.out.println(each.getCreditCardHolder()+" "+each.getCreditCardPin());
                        }

                    }
                    System.out.println("Updated Successfully");
                    break;
                case 4:
                    System.out.println("Name    Old Limit   NewLimit");
                    for (CreditCard each: myBank){

                        if(each.getDateOfBillGeneration().getDate() == 5) {
                            System.out.print(each.getCreditCardHolder() + " " + each.getCreditCardLimit()+" ");
                            each.setCreditCardLimit((int)(each.getCreditCardLimit()+(each.getCreditCardLimit()*0.5)));
                            System.out.println(each.getCreditCardLimit());
                        }
                    }
                    break;

                default:
                     System.out.println("------Thank You------");
                     System.exit(0);
            }

        }
    }
    public void filterLimit(CreditCard[] customer,int startLimit,int endLimit){
        System.out.println("Customers having the CreditLimit between "+startLimit+" and "+endLimit );
        for(CreditCard each: customer){
            if(each.getCreditCardLimit()>=startLimit && each.getCreditCardLimit()<=endLimit)
                System.out.println(each.getCreditCardHolder()+" "+each.getCreditCardLimit());
        }

    }

    public void filterBillPayments(CreditCard[] customers, int start, int end) {
        System.out.println("Customers those who made the bill payments between "+start+" and "+end);

        for (CreditCard each : customers) {
            if (each.getDateOfBillGeneration().getDate() >= start && each.getDateOfBillGeneration().getDate() <= end) {
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
            }
        }
    }
}
