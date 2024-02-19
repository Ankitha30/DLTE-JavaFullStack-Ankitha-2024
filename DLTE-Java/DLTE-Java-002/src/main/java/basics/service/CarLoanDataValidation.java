package basics.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarLoanDataValidation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="",aadhaar="",mobileNumber="";



        System.out.println("Fill your name");
        borrowerName=s.nextLine();
        String nameExpression="^[a-zA-Z .']+$";
        Pattern pattern=Pattern.compile(nameExpression);
        Matcher matcher=pattern.matcher(borrowerName);
        if(matcher.matches())
            System.out.println("valid name");
        else
            System.out.println("Invalid Name");

        System.out.println("fill your aadhar number");
        aadhaar=s.next();
        String aadhaarNumberExpression="^[0-9]{12}$";
        pattern=Pattern.compile(aadhaarNumberExpression);
        matcher=pattern.matcher(aadhaar);
        if(matcher.matches())
            System.out.println("valid Aadhaar Number");
        else
            System.out.println("Invalid Aadhar number");

        System.out.println("enter the PAN number");
        borrowerPan=s.next();
        String panExpression="^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
        pattern=Pattern.compile(panExpression);
        matcher=pattern.matcher(borrowerName);
        if(matcher.matches())
            System.out.println("valid name");
        else
            System.out.println("Invalid Name");


        System.out.println("mention the mobile number");
        mobileNumber=s.next();
        String mobileExpression="\\d{10}";
        pattern=Pattern.compile(mobileExpression);
        matcher=pattern.matcher(mobileNumber);
        if(matcher.matches())
            System.out.println("valid mobile number");
        else
            System.out.println("Invalid mobile number");

        System.out.println("enter the email address");
        borrowerEmail=s.next();
        int atrate=borrowerEmail.indexOf('@');
        int dot= borrowerEmail.indexOf('.');
        if((dot-atrate)>3)
            System.out.println("valid email");
        else
            System.out.println("Invalid mail");

        System.out.println("Let Us know the Income type(salaried/self-employed)");
        borrowerIncomeType=sc.nextLine();

        System.out.println(borrowerName+" Thanks for showing interest on taking loan in My Bank your application has been submitted and further details will be mailed to you "+borrowerEmail+" or sms to "+mobileNumber);
    }
}
