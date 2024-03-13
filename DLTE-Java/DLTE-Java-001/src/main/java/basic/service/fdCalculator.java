package basic.service;

import java.util.Scanner;

public class fdCalculator {
    public static void main(String[] args) {
        Double simpleInterest,principle,tenure,rateOfInterest;
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ---------Welcome To FD Calculator ----------");
        System.out.println("Let me know your name");
        name=scanner.nextLine();
        System.out.println("Enter the principle");
        principle=scanner.nextDouble();
        System.out.println("Enter the rate of interest");
        rateOfInterest=scanner.nextDouble();
        System.out.println("Enter the tenure");
        tenure=scanner.nextDouble();
        simpleInterest=principle+(principle*rateOfInterest*tenure/100);
        System.out.print(name+" Simple Interest FD is ");
        System.out.println(simpleInterest);
        System.out.println("-----Thank You-------");

}

}
