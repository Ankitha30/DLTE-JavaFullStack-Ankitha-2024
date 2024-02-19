package basic.service;

import java.util.Scanner;


public class PersonalLoan {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        Double n=0D;
        System.out.println("------------WelCome--------");
        System.out.println("Fill your name");
        borrowerName=s.nextLine();
        System.out.println("Let Us know the Income type(salaried/self-employed)");
        borrowerIncomeType=s.nextLine();
        System.out.println("fill your aadhar number");
        aadhaar=s.nextLong();
        System.out.println("enter the PaN number");
        borrowerPan=s.next();
        System.out.println("mention the mobile number");
        mobileNumber=s.nextLong();
        System.out.println("enter the email address");
        borrowerEmail=s.next();
        System.out.println(borrowerName+" Thanks for showing interest on taking loan in My Bank your application has been submitted and further details will be mailed to you "+borrowerEmail+" or sms to "+mobileNumber);
    }
}
