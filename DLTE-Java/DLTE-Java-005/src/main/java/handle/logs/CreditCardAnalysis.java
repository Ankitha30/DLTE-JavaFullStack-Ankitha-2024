package handle.logs;

import java.util.Date;
import java.util.Scanner;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardAnalysis {
    public static void main(String[] args) {
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        CreditCard[] myBank={
                new CreditCard(8765678765678L,"Ankitha",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(2343234345445L,"Divija",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                new CreditCard(8765678764545L,"Eeksha",new Date(2031,5,15),955,400000,new Date(2024,3,05),new Date(2024,03,5),9864),
                new CreditCard(1234565456767L,"Akshatha",new Date(2028,8,11),767,700000,new Date(2024,3,18),new Date(2024,03,5),1945),
        };
        CreditCardAnalysis analysis=new CreditCardAnalysis();
//        System.out.println("---------WelCome--------------");
        System.out.println(resourceBundle.getString("welcome.message"));
        System.out.println();
        Scanner scanner = new Scanner(System.in);


            System.out.println(resourceBundle.getString("menu.title"));
            System.out.println(resourceBundle.getString("menu.options"));
            System.out.println(resourceBundle.getString("choice.entry"));
            int choice;

            choice = scanner.nextInt();
            switch (choice) {
                case 1:try {
                    System.out.println(resourceBundle.getString("start.limit"));
                    int startLimit = scanner.nextInt();
                    System.out.println(resourceBundle.getString("end.limit"));
                    int endLimit = scanner.nextInt();
                    analysis.filterLimit(myBank, startLimit, endLimit);
                }catch(MyBankCreditCardException creditCardException){
                    System.out.println("None");
                    logger.log(Level.WARNING,creditCardException.toString());
                }
                    break;
                case 2:
                    try {
                        System.out.println(resourceBundle.getString("start.date"));
                        int start = scanner.nextInt();
                        System.out.println(resourceBundle.getString("end.date"));
                        int end = scanner.nextInt();
                        analysis.filterBillPayments(myBank, start, end);
                    }catch (MyBankCreditCardException creditCardException){
                        System.out.println("None");
                        logger.log(Level.WARNING,creditCardException.toString());
                    }
                    break;
                 default:
                     System.out.println("------Thank You------");
                     scanner.close();
                     System.exit(0);
            }


        }



    public void filterLimit(CreditCard[] customer,int startLimit,int endLimit){
        int flag=0;
        System.out.println("Customers having the CreditLimit between "+startLimit+" and "+endLimit );
        for(CreditCard each: customer){
            if(each.getCreditCardLimit()>=startLimit && each.getCreditCardLimit()<=endLimit) {
                flag=1;
                System.out.println(each.getCreditCardHolder() + " " + each.getCreditCardLimit());
            }
        }
        if(flag==0) {
//            System.out.println("null");
            throw new MyBankCreditCardException();
        }

    }

    public void filterBillPayments(CreditCard[] customers, int start, int end) {
        int flag=0;
        System.out.println("Customers those who made the bill payments between "+start+" and "+end);

        for (CreditCard each : customers) {
            if (each.getDateOfBillPayment().getDate() >= start && each.getDateOfBillPayment().getDate() <= end) {
                flag=1;
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillPayment().getDate());
            }
        }
        if(flag==1)
            throw new MyBankCreditCardException();
    }
}
