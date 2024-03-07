package org.example;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionAnalysis implements Runnable {
    Lock lock = new ReentrantLock();
    Transaction myTransaction[] =
            {
                    new Transaction(new Date(2024, 04, 02), 1000, "Divija", "Family"),
                    new Transaction(new Date(2024, 03, 01), 1020, "Eeksha", "Education"),
                    new Transaction(new Date(2024, 04, 03), 2000, "Akshatha", "Bill"),
                    new Transaction(new Date(2024, 04, 23), 500, "Suni", "Friend"),
                    new Transaction(new Date(2024, 05, 02), 1400, "Suvi", "Family"),
                    new Transaction(new Date(2024, 03, 12), 900, "Suni", "Friend"),
            };
    public  void run(){
        lock.lock();
        System.out.println("-------------------------------Welcome-------------------------");
        System.out.println("****Menu*****");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        TransactionAnalysis analysis = new TransactionAnalysis();
        while (true) {
            System.out.println(
                    "1. Filter based on given ranges of date\n" +
                            "2. least amount transferred\n" +
                            "3. maximum amount transferred\n" +
                            "4. number of transaction made to particular beneficiary\n" +
                            "5. filter based on particular remarks\n" +
                            "6. Exit\n"
            );
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the start Date(enter only date)");
                    int start = scanner.nextInt();
                    System.out.println("Enter the to date");
                    int end = scanner.nextInt();
                    analysis.filterDate(myTransaction, start, end);
                    break;
                case 2:
                    analysis.minimumAmount(myTransaction);
                    break;
                case 3:
                    analysis.maximumAmount(myTransaction);
                    break;
                case 4:
                    System.out.println("Enter the name of Beneficiary");
                    String name = scanner1.nextLine();
                    analysis.totalTransaction(myTransaction, name);
                    break;
                case 5:
                    System.out.println("Enter the remark(Family, Education, Emergency, Bills, Friend)");
                    String remark = scanner1.nextLine();
                    for (Transaction each : myTransaction) {
                        if (each.getRemarks().equals(remark)) {
                            System.out.println(each.getBeneficiary() + " " + each.getAmountInTransaction() + " " + each.getDateOfTransaction());

                        }
                    }
                    break;
                default:
                    return;
            }
            lock.unlock();
        }


    }

    public void totalTransaction (Transaction[]myTransaction, String name){
        int transactioncount = 0;
        for (Transaction each : myTransaction) {
            if (each.getBeneficiary().equals(name))
                transactioncount++;
        }
        System.out.println("Number of Transaction made to " + name + " is " + transactioncount);
    }

    public void minimumAmount (Transaction[]myTransaction){
        int amount = myTransaction[0].getAmountInTransaction();
        for (Transaction each : myTransaction) {
            if (amount > each.getAmountInTransaction())
                amount = each.getAmountInTransaction();
        }
        System.out.println("Name       MinAmount");
        for (Transaction each : myTransaction) {
            if (each.getAmountInTransaction() == amount)
                System.out.println(each.getBeneficiary() + " " + amount);
        }
    }

    public void maximumAmount (Transaction[]myTransaction){
        int amount = myTransaction[0].getAmountInTransaction();
        for (Transaction each : myTransaction) {
            if (amount < each.getAmountInTransaction())
                amount = each.getAmountInTransaction();
        }
        System.out.println("Name       MaxAmount");
        for (Transaction each : myTransaction) {
            if (each.getAmountInTransaction() == amount)
                System.out.println(each.getBeneficiary() + " " + amount);
        }
    }


    public void filterDate (Transaction[]myTransaction,int start, int end){
        System.out.println("Transaction between the given dates " + start + " and " + end);

        for (Transaction each : myTransaction) {
            if (each.getDateOfTransaction().getDate() >= start && each.getDateOfTransaction().getDate() <= end) {
                System.out.println(each.getBeneficiary() + " " + each.getAmountInTransaction() + " " + each.getDateOfTransaction().getDate());
            }
        }
    }


    public void displayAmount() {
        System.out.println("Transaction Amount");
        for (Transaction each: myTransaction)
            System.out.println(each.getAmountInTransaction());
        System.out.println();
    }

    public void displayBeneficiaryNames() {
        System.out.println("Beneficiary Names");
        for (Transaction each: myTransaction)
            System.out.println(each.getBeneficiary());
        System.out.println();
    }

    public void displayDetails() {
        System.out.println("Details:");
        System.out.println("Name   Date                        Amount");
        for (Transaction each: myTransaction)
            System.out.println(each.getBeneficiary()+" "+each.getDateOfTransaction()+" "+each.getAmountInTransaction());

    }
}
