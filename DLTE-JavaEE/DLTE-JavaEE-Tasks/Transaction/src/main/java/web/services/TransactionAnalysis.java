package web.services;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(value="/transaction/obj/*")
public class TransactionAnalysis  extends HttpServlet {
    List<Transaction> myTransactions = Stream.of(new Transaction(1000,new Date("3/2/2022"),"Anu","Family"),
            new Transaction(20000.0,new Date(2/3/2022), "Latha", "Education"),
            new Transaction(3000.0,new Date(3/3/2022),"Jaya","Education")
    ).collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String requestMin = req.getParameter("Minimum Amount");
        String requestMax = req.getParameter("Maximum Amount");
        if(requestMax!=null && requestMin!=null){
            double min = Double.parseDouble(requestMin);
            double max = Double.parseDouble(requestMax);
            Gson gson= new Gson();
            resp.setContentType("application/json");
            List<Transaction> rangeTransaction= myTransactions.stream().filter(each->each.getAmountInTransaction()>min && each.getAmountInTransaction()<max).collect(Collectors.toList());
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(rangeTransaction);
        }
        else
        {
            Gson gson= new Gson();
            String json = gson.toJson(myTransactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        myTransactions.add(transaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Transaction has been added to the records");
    }
}
