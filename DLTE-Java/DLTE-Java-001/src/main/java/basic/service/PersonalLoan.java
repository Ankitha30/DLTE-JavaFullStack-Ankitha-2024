package basic.service;

import java.util.Scanner;


public class PersonalLoan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="",borrowerVoterId="";
        Long mobileNumber=0L,aadhaar=0L;
        Double n=0D;
        System.out.println("------------WelCome--------");
        System.out.println("Enter  your name");
        borrowerName=scanner.nextLine();
        System.out.println("enter the email address");
        borrowerEmail=scanner.next();
        System.out.println("Enter the  address ");
        borrowerAddress=scanner.nextLine();
        System.out.println("Let Us know the Income type(salaried/self-employed)");
        borrowerIncomeType=scanner.nextLine();
        System.out.println("fill your aadhar number");
        aadhaar=scanner.nextLong();
        System.out.println("enter the PAN number");
        borrowerPan=scanner.next();
        System.out.println("mention the mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the voterID number");
        borrowerVoterId=scanner.next();


        System.out.println(borrowerName+" Thanks for showing interest on taking loan in My Bank your application has been submitted and further details will be mailed to you "+borrowerEmail+" or sms to "+mobileNumber);
    }
}
