//package org.database;
//
//import oracle.jdbc.driver.OracleDriver;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import org.slf4j.Logger;
//
//public class DatabaseConnection {
//    static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    static Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
//    private static Connection connection;
//    public static Connection databaseConnection() throws SQLException {
////        try{
//            Driver driver=new OracleDriver();
//            DriverManager.registerDriver(driver);
//            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
////        } catch (SQLException e) {
////            logger.error(resourceBundle.getString("db.error"));
////            System.out.println("Internal Server error.. Contact Administrator");
////            System.exit(0);
////            //            throw new EmployeeException("System error");
////        }
//        return connection;
//    }
//}


package org.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
    }
}
