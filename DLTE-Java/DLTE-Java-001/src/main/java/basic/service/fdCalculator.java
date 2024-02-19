package basic.service;

import java.util.Scanner;

public class fdCalculator {
    public static void main(String[] args) {
        Double si,p,t,r;
        String name;
        Scanner s= new Scanner(System.in);
        System.out.println(" ---------Welcome To FD Calculator ----------");
        System.out.println("Let me know your name");
        name=s.nextLine();
        System.out.println("Enter the principle");
        p=s.nextDouble();
        System.out.println("Enter the rate of interest");
        r=s.nextDouble();
        System.out.println("Enter the tenure");
        t=s.nextDouble();
        si=p+(p*r*t/100);
        System.out.print(name+" Simple Interest FD is ");
        System.out.println(si);
        System.out.println("-----Thank You-------");

}
}
