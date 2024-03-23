package spring.boot.jdbc.springbootjdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spring.boot.jdbc.springbootjdbc.controller.TransactionController;
import spring.boot.jdbc.springbootjdbc.model.Transaction;
import spring.boot.jdbc.springbootjdbc.service.TransactionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTesting {
    @MockBean
    private TransactionService transactionService;
    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testApproval() throws Exception{
        String request ;
        request="{\n" +
                "        \"transactionId\": 2910,\n" +
                "        \"transactionDate\": \"2023-03-09\",\n" +
                "        \"transactionBy\": \"jaya\",\n" +
                "        \"transactionTo\": \"ankitha\",\n" +
                "        \"transactionAmount\": 5000.0,\n" +
                "        \"remarks\": \"Education\"\n" +
                "    }";
        Transaction transaction = new Transaction(2910,new Date("09/03/2023"),"jaya","ankitha",5000.0,"Education");
        when(transactionService.apiSave(any())).thenReturn(transaction);
        mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(request)).andExpect(status().isOk());





    }

    @Test
    void testFindBySender() throws Exception{
        Transaction transaction = new Transaction(2911,new Date("09/09/2023"),"jaya","ankitha",5000.0,"Education");
        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionService.apiFindBySender(eq("jaya"))).thenReturn(transactions);

//        when(transactionService.apiFindBySender(eq("jaya"))).thenReturn((List<Transaction>) transaction);
        mockMvc.perform(get("/transaction/sender/jaya"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(2911))
                .andExpect(jsonPath("$[0].transactionDate").value("2023-09-08T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionBy").value("jaya"))
                .andExpect(jsonPath("$[0].transactionTo").value("ankitha"))
                .andExpect(jsonPath("$[0].transactionAmount").value(5000.0))
                .andExpect(jsonPath("$[0].remarks").value("Education"));

    }


    @Test
       void testFindByReceiver() throws Exception{
        Transaction transaction = new Transaction(3010,new Date("10/09/2023"),"latha","manu",3000.0,"family");
        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionService.apiFindByReceiver(eq("manu"))).thenReturn(transactions);

//        when(transactionService.apiFindByReceiver(eq("jaya"))).thenReturn(transaction);   // testcase fails
        mockMvc.perform(get("/transaction/receiver/manu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(3010))
                .andExpect(jsonPath("$[0].transactionDate").value("2023-10-08T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionBy").value("latha"))
                .andExpect(jsonPath("$[0].transactionTo").value("manu"))
                .andExpect(jsonPath("$[0].transactionAmount").value(3000.0))
                .andExpect(jsonPath("$[0].remarks").value("family"));

    }

    @Test
    void testFindByAmount() throws Exception{
        List<Transaction> transactions =new ArrayList<>();
        transactions.add(new Transaction(3010,new Date("10/09/2023"),"latha","manu",3000.0,"family"));
        transactions.add(new Transaction(3011,new Date("10/23/2023"),"rama","asha",90000.0,"family"));
        when(transactionService.apiFindByAmount(eq(3000.0))).thenReturn(transactions);

        when(transactionService.apiFindByReceiver(eq("jaya"))).thenReturn(transactions);   // testcase fails
        mockMvc.perform(get("/transaction/amount/3000.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(3010))
                .andExpect(jsonPath("$[0].transactionDate").value("2023-10-08T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionBy").value("latha"))
                .andExpect(jsonPath("$[0].transactionTo").value("manu"))
                .andExpect(jsonPath("$[0].transactionAmount").value(3000.0))
                .andExpect(jsonPath("$[0].remarks").value("family"));


            // testcase fails
//        mockMvc.perform(get("/transaction/amount/90000.0"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[1].transactionId").value(3010))
//                .andExpect(jsonPath("$[1].transactionDate").value("2023-10-08T18:30:00.000+00:00"))
//                .andExpect(jsonPath("$[1].transactionBy").value("latha"))
//                .andExpect(jsonPath("$[1].transactionTo").value("manu"))
//                .andExpect(jsonPath("$[1].transactionAmount").value(3000.0))
//                .andExpect(jsonPath("$[1].remarks").value("family"));

    }



}
