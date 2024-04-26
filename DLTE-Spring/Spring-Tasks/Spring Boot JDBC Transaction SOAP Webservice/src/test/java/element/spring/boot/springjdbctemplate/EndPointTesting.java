//package element.spring.boot.springjdbctemplate;
//
//import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
//import element.spring.boot.springjdbctemplate.configs.SoapPhase;
//import element.spring.boot.springjdbctemplate.model.Transaction;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import transaction.services.*;
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import java.text.ParseException;
//import java.time.Instant;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EndPointTesting {
//    @MockBean
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private SoapPhase soapPhase;
//
//    @Test
//    public void testUpdateRemarks(){
//        Transaction transaction = new Transaction(234,new Date("08/09/2023"),"Anu","Suvi", 2000,"Education");
//        Transaction transaction1 = new Transaction(134,new Date("01/02/2023"),"Suni","Suvi", 6000,"family");
//        when(transactionService.apiUpdateTransaction(any(Transaction.class))).thenReturn(transaction);
//        UpdateRemarksRequest request = new UpdateRemarksRequest();
//        Transactions transactions = new Transactions();
//        transactions.setRemarks("rent");
//        request.setTransaction(transactions);
//        UpdateRemarksResponse response =soapPhase.updateByRemarks(request);
//        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
//
//    }
//
//    @Test
//    public void testFilterBySender(){
//        Transaction transaction = new Transaction(234,new Date("08/09/2023"),"Anu","Suvi", 2000,"Education");
//        Transaction transaction1 = new Transaction(134,new Date("01/02/2023"),"Suni","Suvi", 6000,"family");
//        List<Transaction> expectedTransaction = Stream.of(transaction,transaction1).collect(Collectors.toList());
//        when(transactionService.apiFindBySender(any())).thenReturn(expectedTransaction);
//        FilterBySenderRequest request =new FilterBySenderRequest();
//        request.setTransactionBy("Anu");
//        FilterBySenderResponse response = soapPhase.filterBySender(request);
////        assertEquals("Transaction by sender "+request.getTransactionBy()+" is fetched", response.getServiceStatus().getMessage()); //pass
//        assertNotEquals("FAILURE", response.getServiceStatus().getStatus());
//
//    }
//
//    @Test
//    public void testFilterByAmount(){
//        Transaction transaction = new Transaction(234,new Date("08/09/2023"),"Anu","Suvi", 2000.0,"Education");
//        Transaction transaction1 = new Transaction(134,new Date("01/02/2023"),"Suni","Suvi", 6000.0,"family");
//        List<Transaction> expectedTransaction = Stream.of(transaction,transaction1).collect(Collectors.toList());
//        when(transactionService.apiFindBySender(any())).thenReturn(expectedTransaction);
//        FilterByAmountRequest request =new FilterByAmountRequest();
//        request.setTransactionAmount(2000.0);
//        FilterByAmountResponse response = soapPhase.filterByAmount(request);
//        assertEquals("Transaction by amount "+request.getTransactionAmount()+" is fetched", response.getServiceStatus().getMessage());
////        assertEquals(request.getTransactionAmount(), response.getServiceStatus().getMessage()); //fail
////        assertNotEquals("FAILURE", response.getServiceStatus().getStatus());  //pass
//         }
//
//
//    @Test
//    public void testFilterByReceiver(){
//        Transaction transaction = new Transaction(234,new Date("08/09/2023"),"Anu","Suvi", 2000,"Education");
//        Transaction transaction1 = new Transaction(134,new Date("01/02/2023"),"Suni","Suvi", 6000,"family");
//        List<Transaction> expectedTransaction = Stream.of(transaction,transaction1).collect(Collectors.toList());
//        when(transactionService.apiFindBySender(any())).thenReturn(expectedTransaction);
//        FilterByReceiverRequest request =new FilterByReceiverRequest();
//        request.setTransactionTo("Anu");
//        FilterByReceiverResponse response = soapPhase.filterByReceiver(request);
//        assertEquals("Transaction by receiver "+request.getTransactionTo()+" is fetched", response.getServiceStatus().getMessage());
////        assertEquals(request.getTransactionAmount(), response.getServiceStatus().getMessage()); //fail
////        assertNotEquals("FAILURE", response.getServiceStatus().getStatus());  //pass
//        }
//
//
//
//
//    @Test
//    public void testNewTransaction() throws ParseException, DatatypeConfigurationException {
//        Transaction transaction = new Transaction(234,new Date("08/09/2023"),"Anu","Suvi", 2000,"Education");
//        Transaction transaction1 = new Transaction(134,new Date("01/02/2023"),"Suni","Suvi", 6000,"family");
//        when(transactionService.apiSave(any(Transaction.class))).thenReturn(transaction);
//        NewTransactionrequest request = new NewTransactionrequest();
//        Transactions transactions = new Transactions();
//        transactions.setTransactionId(234);
//        transactions.setTransactionBy("Anu");
//        transactions.setTransactionTo("Suvi");
//        transactions.setTransactionAmount(2000);
//        transactions.setRemarks("Education");
//        transactions.setTransactionDate(XMLGregorianCalendarImpl.createDate(2023,8,9, 0));
//
//        NewTransactionresponse transactionResponse =soapPhase.addNewTransaction(request);
//        assertTrue(transaction.getTransactionBy().equals(transactionResponse.getTransactions().getTransactionBy()));
//
//
//    }
//    @Test
//    public void testDelete(){
//        Date startDate = Date.from(Instant.parse("2024-01-01T00:00:00Z"));
//        Date endDate = Date.from(Instant.parse("2024-01-15T23:59:59Z"));
//        when(transactionService.apiDeleteTransaction(startDate, endDate)).thenReturn("deleted");
//        DeleteByRangeOfTransactionRequest request = new DeleteByRangeOfTransactionRequest();
//        XMLGregorianCalendar startCal = XMLGregorianCalendarImpl.createDateTime(2024, 1, 1, 0, 0, 0, 0, 0);
//        XMLGregorianCalendar endCal = XMLGregorianCalendarImpl.createDateTime(2024, 1, 15, 23, 59, 59, 0, 0);
//        request.setStartDate(startCal);
//        request.setEndDate(endCal);
//
//        DeleteByRangeOfTransactionResponse response = soapPhase.deleteBasedOnDates(request);
//
////     assertEquals("SUCCESS", response.getServiceStatus().getStatus());
////     assertEquals("FAILURE", response.getServiceStatus().getStatus());
//        assertEquals("deleted", response.getServiceStatus().getMessage());
//    }
//
//}
