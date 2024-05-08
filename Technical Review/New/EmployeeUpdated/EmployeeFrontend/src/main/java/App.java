import entity.AddressDetails;
import entity.PersonalDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import middleware.CollectDetails;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger= LoggerFactory.getLogger(App.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static void main( String[] args )
    {

//        CollectDetails collectDetails = new CollectDetails();
//        DataAnalyzer dataAnalyzer =new DataAnalyzer();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(resourceBundle.getString("menu.options"));
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        CollectDetails.addEmployeeDetails();

                        break;
                    case 2:
                        CollectDetails.displayDetails();
                        break;
                    case 3:
                        CollectDetails.searchById();
                        break;
                    case 4:
                        CollectDetails.searchByPinCode();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        logger.warn("Invalid Choice entered");
                        System.out.println("Invalid Choice! try again");
                        break;

                }
            }catch (InputMismatchException | SQLException err)
            {
                logger.error("Input mismatch occurred ");
                System.out.println("Input mismatch");
                scanner.nextLine();
            }



        }
    }
}
