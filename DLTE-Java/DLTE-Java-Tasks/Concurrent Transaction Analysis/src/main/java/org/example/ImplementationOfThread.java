package org.example;

public class ImplementationOfThread {
    public static void main(String[] args) {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        Thread thread1 = new Thread(transactionAnalysis::displayAmount,"thread1");
        thread1.start();
        Thread thread2 = new Thread(transactionAnalysis::displayBeneficiaryNames,"thread2");
        thread2.start();
        Thread thread3 = new Thread(transactionAnalysis::displayDetails,"thread2");
        thread3.start();
        Thread thread4= new Thread(transactionAnalysis,"thread4");
        Thread thread5= new Thread(transactionAnalysis,"thread5");
        Thread thread6= new Thread(transactionAnalysis,"thread6");
        Thread thread7= new Thread(transactionAnalysis,"thread7");
        Thread thread8= new Thread(transactionAnalysis,"thread8");
        Thread thread9= new Thread(transactionAnalysis,"thread9");
        Thread thread10= new Thread(transactionAnalysis,"thread10");
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

    }
}
