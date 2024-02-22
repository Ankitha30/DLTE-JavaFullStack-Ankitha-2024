package handle.logs;



import java.util.Scanner;

public class GPay extends Account {
    private Integer upiPin;

    public GPay(String accountHolder, Long accountNumber, Double accountBalance, Integer upiPin, String username) {
        super(accountHolder, accountNumber, accountBalance);
        this.upiPin = upiPin;
        this.username = username;
    }

    private String username;


    public void payBills(String billerName, Double billAmount, String billType) {
        Scanner scanner = new Scanner(System.in);
        Integer pin;
        int count = 0;
        while (count < 5) {
            System.out.println("Enter the UPI Pin");
            pin = scanner.nextInt();
            if (pin.equals(upiPin)) {
                accountBalance -= billAmount;
                System.out.println(billerName + ", " + billType + " " + " bill is paid Successfully");
                System.out.println("Thank you");
                break;
            } else {
                count++;
            }
        }
        if (count >= 5) {
            throw new MyBankException();
        }
    }
}