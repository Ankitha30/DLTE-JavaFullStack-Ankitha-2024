package basics.service;

import java.sql.SQLOutput;
import java.util.Scanner;

public class SipCalculator {
    public static void main(String[] args) {
//        Double maturityAmount=0.0,principle=0.0,rateofInterest=0.0;
       Double maturityAmount=0.0,principle=0.0,compoundinterest=0.0,rateofInterest=0.0;
        Scanner s = new Scanner(System.in);
        int numberofPayments;
        System.out.println("-----Welcome to SIP calculator-------");
        System.out.println("Enter the principle ");
        principle=s.nextDouble();
        System.out.println("Enter the rate of interest");
        rateofInterest = s.nextDouble();
        System.out.println("Enter the number of payments you have made");
        numberofPayments=s.nextInt();
        compoundinterest=rateofInterest/(12*100);
        Double multiplier = Math.pow(compoundinterest+1,numberofPayments);
        maturityAmount= principle *((multiplier-1)/compoundinterest)*(1+compoundinterest);
        System.out.println(maturityAmount+" Rs per year");
    }
}
