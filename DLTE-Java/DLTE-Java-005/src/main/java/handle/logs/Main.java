package handle.logs;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        GPay gPay=new GPay("Ankitha", 123456789L, 23000D, 123, "Ankitha");

        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        FileHandler fileHandler=new FileHandler("C:\\Users\\xxbhatka\\Documents\\credit-card-logs.txt",true);
        SimpleFormatter simpleFormatter=new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
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
     /*
        try{

        }
        catch (IOException ioException){}
    }
      */

    }

}
