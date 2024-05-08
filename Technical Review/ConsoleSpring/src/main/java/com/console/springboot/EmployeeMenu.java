package com.console.springboot;

//import org.database.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;



//@SpringBootApplication
public class EmployeeMenu {


    @Autowired
//    static EmployeeRepo employeeRepo;

    private static Logger logger = LoggerFactory.getLogger(EmployeeMenu.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


    public EmployeeMenu() throws SQLException {
    }

    public static void main(String[] args) throws SQLException, EmployeeException {
//        SpringApplication.run(EmployeeMenu.class, args);
        CollectAndDisplayDetails app = new CollectAndDisplayDetails();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(resourceBundle.getString("menu.options"));
//            logger.info(resourceBundle.getString("menu.options"));
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        logger.info("Option 1 is selected");
                        app.addEmployeeDetails();
                        break;
                    case 2:
                        logger.info("Option 2 is selected");
                        app.displayDetails();
                        break;
                    case 3:
                        logger.info("Option 3 is selected");
                        app.searchById();
                        break;
                    case 4:
                        logger.info("Option 4 is selected");
                        app.searchByPinCode();
                        break;
                    case 5:
                        logger.info("Option 5(EXit) is selected");
//                        app.closeAll();
                        System.exit(0);
                    default:
                        logger.warn("Invalid Choice entered");
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        break;
                }
            } catch (InputMismatchException | IOException err) {
//                logger.error("Input mismatch occurred ");
                err.printStackTrace();
                System.out.println(resourceBundle.getString("invalid.choice"));
                scanner.nextLine();
            }
        }
    }
}
