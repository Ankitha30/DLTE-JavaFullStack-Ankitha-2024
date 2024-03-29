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
import java.sql.Date;
import java.util.List;

@WebServlet("/findAll/dateNuser/*")
public class FindAllByDateAndUsername extends HttpServlet {
    public TransactionService transactionService;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String reqDate = req.getParameter("date");

       String reqUser=req.getParameter("user");
        try{
            List<Transaction> transaction = transactionService.callFindByDateNUsername(Date.valueOf(reqDate),reqUser);
            Gson gson=new Gson();
            String response = gson.toJson(transaction);
            resp.getWriter().println(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);

    }
}
