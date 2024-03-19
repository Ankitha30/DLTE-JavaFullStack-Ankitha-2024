package org.servlets;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAll/username/")
public class FindAllByUserName extends HttpServlet {
    public TransactionService transactionService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           resp.setContentType("application/json");
           String userName = req.getParameter("username");
           List<Transaction> transactions = transactionService.callViewTransaction(userName);
           Gson gson = new Gson();
           String response = gson.toJson(transactions);
           System.out.println(response);
           resp.setStatus(HttpServletResponse.SC_OK);
           resp.getWriter().println(response);
       }catch (Exception e){
           resp.getWriter().println("not found");
       }
    }

    @Override
    public void init() throws ServletException {
//        super.init();
        StorageTarget storageTarget = new DatabaseTarget();
        transactionService = new TransactionService(storageTarget);
    }
}
