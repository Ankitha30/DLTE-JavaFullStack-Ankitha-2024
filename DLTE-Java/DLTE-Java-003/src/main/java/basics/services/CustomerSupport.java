package basics.services;

import java.util.Date;

public class CustomerSupport {
    public static void main(String[] args) {
        CreditCard creditCard=new CreditCard();
       creditCard.setCreditCardNumber(567768798l);
       creditCard.setCreditCardExpiry(new Date(2034,12,20));
       creditCard.setCreditCvv(234);
       creditCard.setCreditCardPin(2345);
       creditCard.setCreditCardHolder("Anu");
       creditCard.setCreditCardLimit(10000);
       creditCard.setDateofBilgeneration(new Date(2024,03,11));
       creditCard.setDateOfBillPayment(new Date(2024,03,25));
    }
}
