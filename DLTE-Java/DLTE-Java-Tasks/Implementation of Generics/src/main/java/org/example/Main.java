package org.example;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        MyBankDatabase<Transaction> transactionMyBankDatabase = new MyBankDatabase<>();
        MyBankDatabase<CreditCard> creditCardMyBankDatabase= new MyBankDatabase<>();
        creditCardMyBankDatabase.typeObject=new CreditCard[5];
        CreditCard creditCard1=new CreditCard(2345432L, "Ankitha", new Date("2/12/2028"), 711, 100000, 1233);
        CreditCard creditCard2=new CreditCard(2343443L, "Sunidhi", new Date("4/22/2029"), 231, 100000, 1193);
        CreditCard creditCard3=new CreditCard(5643443L, "Suvidha", new Date("3/2/2027"), 531, 100000, 9093);
        CreditCard creditCard4=new CreditCard(3446743L, "Nidhi", new Date("10/2/2030"), 238, 50000, 2222);


        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard1));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard2));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard3));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard4));
        System.out.println("After insertion: ");
        creditCardMyBankDatabase.viewAll();
        creditCardMyBankDatabase.update(2,creditCard4);
        System.out.println("After update");
        creditCardMyBankDatabase.viewAll();

        System.out.println(creditCardMyBankDatabase.delete(3));
        System.out.println("After delete: ");
        creditCardMyBankDatabase.viewAll();
//        Transaction transaction1=new Transaction();
        transactionMyBankDatabase.typeObject = new Transaction[5];
        Transaction transaction1=new Transaction(12, new Date("2/12/2023"), "Ankitha", 1200.0, "Family");
        Transaction transaction2=new Transaction(13, new Date("2/13/2023"), "Anu", 2200.0, "Family");
        Transaction transaction3=new Transaction(14, new Date("2/14/2023"), "Amitha", 1300.0, "Friend");
        Transaction transaction4=new Transaction(15, new Date("2/15/2023"), "Anitha", 4200.0, "Education");
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction1));
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction2));
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction3));
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction4));
        System.out.println("After insertion: ");
        transactionMyBankDatabase.viewAll();

        transactionMyBankDatabase.update(1, transaction4);
        System.out.println("After update");
        transactionMyBankDatabase.viewAll();
        transactionMyBankDatabase.delete(3);
        System.out.println("After delete: ");
        transactionMyBankDatabase.viewAll();




    }
}
