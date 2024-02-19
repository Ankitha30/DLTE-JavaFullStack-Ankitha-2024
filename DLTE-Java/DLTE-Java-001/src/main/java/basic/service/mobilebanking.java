package basic.service;

import java.util.Scanner;

public class mobilebanking {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String Name="",panNum="",Email="",accountNumber="",password="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("---------Welcome to Mobile Banking---------");
        System.out.println("Enter  your User name/Email");
        Name=s.nextLine();
        System.out.println("Enter you mpin");
        password=s.next();
        System.out.println("Enter your account number");
        accountNumber=s.next();
        System.out.println("fill your aadhar number");
        aadhaar=s.nextLong();
        System.out.println("enter the PaN number");
        panNum=s.next();
        System.out.println("mention the mobile number");
        mobileNumber=s.nextLong();
        System.out.println(Name+" Thanks for showing interest on Mobile Banking");

    }
}
