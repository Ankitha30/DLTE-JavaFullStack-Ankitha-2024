package basic.service;

import java.util.Scanner;

public class NetBanking {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String Name="",panNum="",Email="",accountNumber="",password="",email="";
        Long aadhaar=0L;
        System.out.println("---------Welcome to Net Banking---------");
        System.out.println("Enter  your User name");
        Name=s.nextLine();
        System.out.println("Enter you password");
        password=s.next();
        System.out.println("Enter the email");
        email=s.next();
        System.out.println("Enter your account number");
        accountNumber=s.next();
        System.out.println("fill your aadhaar number");
        aadhaar=s.nextLong();
        System.out.println("enter the PaN number");
        panNum=s.next();
        System.out.println(Name+" Thanks for showing interest on Net Banking");
    }
}















