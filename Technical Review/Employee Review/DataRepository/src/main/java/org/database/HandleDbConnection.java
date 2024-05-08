//package org.database;
//
//import oracle.jdbc.driver.OracleDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class HandleDbConnection {
//    static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    static Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
//    private static Connection connection;
//
//    public static Connection databaseConnection() throws SQLException {
//        try{
////            Driver driver=new OracleDriver();
////            DriverManager.registerDriver(driver);
////            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
//            DatabaseConnection databaseConnection = new DatabaseConnection();
//        } catch (SQLException e) {
//            logger.error(resourceBundle.getString("db.error"));
//            System.out.println("Internal Server error.. Contact Administrator");
//            System.exit(0);
//            //            throw new EmployeeException("System error");
//        }
//        return connection;
//    }
//}

package org.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HandleDbConnection {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    static Logger logger = LoggerFactory.getLogger(HandleDbConnection.class);

    public static Connection databaseConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("db.error"));
            throw e; // Rethrow the SQLException
        }
        return connection;
    }
}
