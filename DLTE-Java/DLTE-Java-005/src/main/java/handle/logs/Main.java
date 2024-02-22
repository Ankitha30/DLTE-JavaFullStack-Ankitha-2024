package handle.logs;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        GPay gPay=new GPay("Ankitha", 123456789L, 23000D, 123, "Ankitha");

        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
     try {
         System.out.println("Enter the biller name");
         String billerName = scanner1.nextLine();
         System.out.println("Enter the  biller type");
         String billType = scanner1.nextLine();
         System.out.println("Enter the bill amount ");
         Double billAmount = scanner1.nextDouble();
         gPay.payBills(billerName, billAmount, billType);
     }catch (MyBankException e){
         logger.log(Level.WARNING,e.toString());
     }

    }

}
