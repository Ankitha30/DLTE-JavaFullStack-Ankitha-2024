package com.console;

import com.exception.EmployeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.database.DatabaseService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeMenu {
    private static Logger logger = LoggerFactory.getLogger(EmployeeMenu.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static DatabaseService databaseService;

    public EmployeeMenu() throws SQLException {
    }

    public static void main(String[] args) throws SQLException, EmployeeException {
        App app = new App();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(resourceBundle.getString("menu.options"));
            logger.info(resourceBundle.getString("menu.options"));
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
                        app.closeAll();
                        System.exit(0);
                    default:
                        logger.warn("Invalid Choice entered");
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        break;
                }
            } catch (InputMismatchException err) {
                logger.error("Input mismatch occurred ");
                System.out.println(resourceBundle.getString("invalid.choice"));
                scanner.nextLine();
            }
        }
    }
}

