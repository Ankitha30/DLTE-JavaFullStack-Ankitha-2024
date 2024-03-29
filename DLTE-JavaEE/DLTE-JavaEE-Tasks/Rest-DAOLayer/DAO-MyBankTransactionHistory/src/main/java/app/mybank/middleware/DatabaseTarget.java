package app.mybank.middleware;

import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget implements StorageTarget {
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    Logger logger= LoggerFactory.getLogger(DatabaseTarget.class);
    private Connection connection;

    public DatabaseTarget(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
            logger.info(resourceBundle.getString("db.success"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public TransactionRepository getTransactionRepository() {
        return new TransactionDatabaseRepository(connection);
    }
}
