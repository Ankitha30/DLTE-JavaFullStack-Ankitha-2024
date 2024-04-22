package sevlet.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import dao.servlets.FindAll;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import  app.mybank.remotes.TransactionRepository;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;


@WebServlet("/authorize")
public class AuhtorizeServlet   extends HttpServlet {
    public TransactionService cardServices;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public TransactionRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        cardServices=new TransactionService(storageTarget);
        resourceBundle=ResourceBundle.getBundle("application");
        logger= LoggerFactory.getLogger(FindAll.class);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            String query = "select * from Account where username=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                HttpSession session=req.getSession();
                session.setAttribute("username",username);
                resp.sendRedirect("dashboard.jsp");
            }
            else{
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException var2) {
            SQLException sqlException = var2;
            System.out.println(sqlException);
        }
    }
}