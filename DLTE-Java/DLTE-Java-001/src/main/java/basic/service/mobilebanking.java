package basic.service;

import java.util.Scanner;

public class mobilebanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name="",panNum="",email="",accountNumber="",password="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("---------Welcome to Mobile Banking---------");
        System.out.println("Enter  your User name/Email");
        name=scanner.nextLine();
        System.out.println("Enter you mpin");
        password=scanner.next();
        System.out.println("Enter your account number");
        accountNumber=scanner.next();
        System.out.println("fill your aadhar number");
        aadhaar=scanner.nextLong();
        System.out.println("enter the PaN number");
        panNum=scanner.next();
        System.out.println("mention the mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println(name+" Thanks for showing interest on Mobile Banking");

    }
}
