package basic.service;

import java.util.Scanner;

public class fdCalculator {
    public static void main(String[] args) {
        Double simpleInterest,principle,tenure,rateOfInterest;
        String name;
        Scanner s= new Scanner(System.in);
        System.out.println(" ---------Welcome To FD Calculator ----------");
        System.out.println("Let me know your name");
        name=s.nextLine();
        System.out.println("Enter the principle");
        principle=s.nextDouble();
        System.out.println("Enter the rate of interest");
        rateOfInterest=s.nextDouble();
        System.out.println("Enter the tenure");
        tenure=s.nextDouble();
        simpleInterest=principle+(principle*rateOfInterest*tenure/100);
        System.out.print(name+" Simple Interest FD is ");
        System.out.println(simpleInterest);
        System.out.println("-----Thank You-------");

}

}
