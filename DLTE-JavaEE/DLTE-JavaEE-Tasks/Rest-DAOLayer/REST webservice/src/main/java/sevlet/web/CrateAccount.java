package sevlet.web;

import app.mybank.entity.Account;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import dao.servlets.CreateAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;


@WebServlet("/add")
public class CrateAccount  extends HttpServlet {
    private TransactionService transactionService;
    public ResourceBundle resourceBundle;
    public Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
        resourceBundle= ResourceBundle.getBundle("application");
        logger= LoggerFactory.getLogger(CreateAccount.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        long phoneNumber=Long.parseLong(req.getParameter("phoneNumber"));
        String email=req.getParameter("email");
        Account account=new Account(name,password,email,phoneNumber);
        RequestDispatcher dispatcher=req.getRequestDispatcher("createAccount.jsp");
        transactionService.callSaveAccount(account);
        req.setAttribute("info","Account is created");
        dispatcher.forward(req, resp);
    }
}