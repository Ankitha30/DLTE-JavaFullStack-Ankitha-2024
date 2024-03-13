package basic.service;

import java.util.Scanner;

public class NetBanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name="",panNum="",Email="",accountNumber="",password="",email="";
        Long aadhaar=0L;
        System.out.println("---------Welcome to Net Banking---------");
        System.out.println("Enter  your User name");
        name=scanner.nextLine();
        System.out.println("Enter you password");
        password=scanner.next();
        System.out.println("Enter the email");
        email=scanner.next();
        System.out.println("Enter your account number");
        accountNumber=scanner.next();
        System.out.println("fill your aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("enter the PaN number");
        panNum=scanner.next();
        System.out.println(name+" Thanks for showing interest on Net Banking");
    }
}















