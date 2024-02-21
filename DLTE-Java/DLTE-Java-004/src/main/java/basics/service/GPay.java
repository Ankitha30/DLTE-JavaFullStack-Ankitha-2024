package basics.service;

import java.util.Scanner;

public class GPay extends DebitCard {
    private Integer upiPin;
    private String username;
    public GPay(String accountHolder, Long accountNumber, Double accountBalance, Long cardNumber, String cardPin, Integer upiPin, String username) {
        super(accountHolder, accountNumber, accountBalance, cardNumber, cardPin);
        this.upiPin = upiPin;
        this.username = username;
    }
    public GPay(String accountHolder, Long accountNumber, Double accountBalance, Integer upiPin, String username) {
        super(accountHolder, accountNumber, accountBalance);
        this.upiPin = upiPin;
        this.username = username;
    }

    public void payBills(String billerName, Double billAmount, String billType) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the UPI Pin");
        int pin = scanner.nextInt();
        if(pin==upiPin)
        {
         accountBalance-=billAmount;
            System.out.println(billerName+", "+billType+" "+" bill is paid Successfully");
            System.out.println("Thank you");
        }
        else
        {
            System.out.println("Entered UPI is Invalid \n Try Again");
        }
    }
}
