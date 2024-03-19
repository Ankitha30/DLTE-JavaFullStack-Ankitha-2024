package rest.test;



 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import static org.junit.Assert.*;
 import static org.mockito.ArgumentMatchers.anyDouble;
 import static org.mockito.ArgumentMatchers.anyString;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;
import app.mybank.entity.Transaction;
import app.mybank.services.TransactionService;
 import org.mockito.Mock;
 import org.mockito.junit.MockitoJUnitRunner;
 import org.servlets.FindAll;
 import org.servlets.FindAllByDateAndUsername;
 import org.servlets.FindAllByUserName;
 import org.slf4j.Logger;
// import rest.endpoint.ModifyService;
// import rest.endpoint.ReadAllByLimitService;
// import rest.endpoint.ReadAllService;
// import rest.endpoint.ReadByIdService;

 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.ResourceBundle;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTesting {
    List<Transaction> transactionList = new ArrayList<>();
    @Mock
    private TransactionService service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;


    @Before
    public  void initiate() throws IOException{
        stringWriter = new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        transactionList.add(new Transaction("Jayashri", "transfer",3400.0, new Date("12/2/2029") ));
        transactionList.add(new Transaction("Jayashri", "deposit",5600.0,new  Date("12/2/2029")));

    }

    @Test
    public  void findAllTest() throws ServletException, IOException {
//        System.out.println(transactionList);
        FindAll findAll=new FindAll();
        findAll.transactionService=service;
        when(service.callViewTransaction()).thenReturn( transactionList);
        findAll.doGet(request,response);
        verify(response).setContentType("application/json");
        verify(service).callViewTransaction();
        assertEquals("[{\"userName\":\"Jayashri\",\"transactionType\":\"transfer\",\"transactionAmount\":3400.0,\"transactionDate\":\"Dec 2, 2029 12:00:00 AM\"},{\"userName\":\"Jayashri\",\"transactionType\":\"deposit\",\"transactionAmount\":5600.0,\"transactionDate\":\"Dec 2, 2029 12:00:00 AM\"}]",stringWriter.toString().trim());
    }

    @Test
    public void testFindAllByDateAndUsername() throws IOException, ServletException {

        FindAllByDateAndUsername findAllByDateAndUsername = new FindAllByDateAndUsername();
        findAllByDateAndUsername.transactionService=service;
        when(request.getParameter("user")).thenReturn("Jayashri");
        when(request.getParameter("startdate")).thenReturn("02/01/2023");
        when(request.getParameter("enddate")).thenReturn("02/27/2023");
        when(service.callFindByDate(anyString(),anyString(),anyString())).thenReturn(transactionList);
        findAllByDateAndUsername.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(service).callFindByDate(anyString(),anyString(),anyString());
        assertEquals("[{\"userName\":\"Jayashri\",\"transactionType\":\"transfer\",\"transactionAmount\":3400.0,\"transactionDate\":\"Dec 2, 2029 12:00:00 AM\"},{\"userName\":\"Jayashri\",\"transactionType\":\"deposit\",\"transactionAmount\":5600.0,\"transactionDate\":\"Dec 2, 2029 12:00:00 AM\"}]",stringWriter.toString().trim());
    }




    @Test
    public void findAllByUserTets() throws ServletException, IOException {
        FindAllByUserName findAllByUserName=new FindAllByUserName();
        findAllByUserName.transactionService=service;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Vamshi","Withdraw",900.0, new Date(03/05/20203)));
        System.out.println(transactions);
        when(request.getParameter("use")).thenReturn("Vamshi");
        when(service.callViewTransaction(anyString())).thenReturn(transactions);
        findAllByUserName.doGet(request,response);
        verify(response).setContentType("applcation/json");
        assertEquals("[{\"userName\":\"Vamshi\',\"transactionType\";\"Withdraw'\',\"transactionAmount\":900.0,\'transactionDate=\":\"Jan 01 05:30:00 IST 1970\"}]",stringWriter.toString().trim());
    }


}
