package basics.services;

import java.util.Date;

public class CustomerSupport {
    public static void main(String[] args) {

//        CreditCard[] myBank=new CreditCard[10];

            CreditCard[] myBank={
                    new CreditCard(8765678765678L,"Ankitha",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                    new CreditCard(2343234345445L,"Divija",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                    new CreditCard(8765678764545L,"Eeksha",new Date(2031,5,15),955,400000,new Date(2024,3,10),new Date(2024,03,11),9864),
                    new CreditCard(1234565456767L,"Akshatha",new Date(2028,8,11),767,700000,new Date(2024,3,18),new Date(2024,03,29),1945),
            };

            CustomerSupport support=new CustomerSupport();
            support.findEarlyExpire(myBank);
            support.findBillDate(myBank,new Date(2024,3,5),new Date(2024,3,18));
            support.list(myBank);
            support.sortingCustomers(myBank);
            System.out.println();
            support.list(myBank);
//            support.filterLimit(myBank,100000,300000);
//            support.filterBillPayments(myBank,new Date(2024,3,5),new Date(2024,3,18));
        }

        public void findEarlyExpire(CreditCard[] customers){
            for(CreditCard each:customers){
                if(each.getCreditCardExpiry().before(new Date(2030,01,01))){
                    System.out.println(each.getCreditCardHolder()+" your credit card "+each.getCreditCardNumber()+" needs to be upgraded");
                }
            }
        }

        public void findBillDate(CreditCard[] customers, Date start, Date end) {
            System.out.println("Customers those having bill dates between " + start.getDate() + " and " + end.getDate());
            for (CreditCard each : customers) {
                if (each.getDateOfBillGeneration().getDate() >= start.getDate() && each.getDateOfBillGeneration().getDate() <= end.getDate()) {
                    System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
                }
            }
        }

//        public void filterLimit(CreditCard[] customer,int startLimit,int endLimit){
//            System.out.println("Customers having the CreditLimit between "+startLimit+" and "+endLimit );
//            for(CreditCard each: customer){
//                if(each.getCreditCardLimit()>=startLimit && each.getCreditCardLimit()<=endLimit)
//                    System.out.println(each.getCreditCardHolder()+" "+each.getCreditCardLimit());
//            }
//
//        }
//    public void filterBillPayments(CreditCard[] customers, Date start, Date end) {
//        System.out.println("Customers those who made the bill payments between " + start.getDate() + " and " + end.getDate());
//        for (CreditCard each : customers) {
//            if (each.getDateOfBillGeneration().getDate() >= start.getDate() && each.getDateOfBillGeneration().getDate() <= end.getDate()) {
//                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
//            }
//        }
//    }

        public void list(CreditCard[] customers){
            System.out.println("Available customers");
            for(CreditCard each:customers){
                System.out.println(each);
            }

        }
        public  void sortingCustomers(CreditCard[] customer){
            for(int select=0;select<customer.length;select++)
            {
                for(int next=select+1;next<customer.length;next++){
                    if(customer[select].getCreditCardHolder().compareTo(customer[next].getCreditCardHolder())>0){
                        CreditCard backup = customer[select];
                        customer[select]=customer[next];
                        customer[next]=backup;
                    }
                }
            }
        }




//        CreditCard creditCard=new CreditCard();
//       creditCard.setCreditCardNumber(567768798l);
//       creditCard.setCreditCardExpiry(new Date(2034,12,20));
//       creditCard.setCreditCvv(234);
//       creditCard.setCreditCardPin(2345);
//       creditCard.setCreditCardHolder("Anu");
//       creditCard.setCreditCardLimit(10000);
//       creditCard.setDateofBilGeneration(new Date(2024,03,11));
//       creditCard.setDateOfBillPayment(new Date(2024,03,25));


    }


