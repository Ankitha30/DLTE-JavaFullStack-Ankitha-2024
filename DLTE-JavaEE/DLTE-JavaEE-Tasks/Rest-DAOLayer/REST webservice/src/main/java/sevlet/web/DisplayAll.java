package sevlet.web;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view")
public class DisplayAll extends HttpServlet {

    public TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transaction> myTransactionList = transactionService.callViewAllTransaction();
        RequestDispatcher dispatcher=req.getRequestDispatcher("viewTransactions.jsp");
        req.setAttribute("myTransactions", myTransactionList);
        dispatcher.include(req, resp);
    }
}