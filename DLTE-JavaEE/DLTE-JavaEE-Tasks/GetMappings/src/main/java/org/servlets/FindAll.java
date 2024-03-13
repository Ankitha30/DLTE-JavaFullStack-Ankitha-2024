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



    @WebServlet("/find/all/")
    public class FindAll extends HttpServlet {
        TransactionService transactionService;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
            resp.setContentType("application/json");
            List<Transaction> transactions = transactionService.callViewTransaction();
            Gson gson=new Gson();
            String response = gson.toJson(transactions);
            resp.getWriter().println(response);
        }

        @Override
        public void init() throws ServletException {
//        super.init();
            StorageTarget storageTarget = new DatabaseTarget();
            transactionService = new TransactionService(storageTarget);
        }

    }

