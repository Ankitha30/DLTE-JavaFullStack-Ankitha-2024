package basics.service;

import java.util.Scanner;

public class DebitCard extends Account {
    private Long cardNumber;



    public DebitCard(String accountHolder, Long accountNumber, Double accountBalance, Long cardNumber, String cardPin) {
        super(accountHolder, accountNumber, accountBalance);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    private String cardPin;

    public DebitCard(String accountHolder, Long accountNumber, Double accountBalance) {
        super(accountHolder, accountNumber, accountBalance);

    }
    public void withdraw(Double amount){
        Scanner scanner=new Scanner(System.in)    ;
        if(amount<accountBalance)
        {
            System.out.println("enter the pin");
            String pin=scanner.next();
            if(pin.equals(cardPin))
            {
                accountBalance-=amount;
            }
        }
        System.out.println("Remaining Balance after withdrawal : "+accountBalance);
    }

}