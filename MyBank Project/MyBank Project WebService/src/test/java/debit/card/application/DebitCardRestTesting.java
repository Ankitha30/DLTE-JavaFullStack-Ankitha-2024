package debit.card.application;

import debit.card.application.configs.DebitCardPhase;
import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import debit.card.application.configs.DebitCardPhase;
import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DebitCardRestTesting {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("cards");


    @MockBean
    private DebitCardService debitCardRepository;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UpdateStatusController updateStatus;




// @Test
//    void testUpdateStatus_Success1() {
//        // Mock successful response from DebitCardRepository
//        when(debitCardRepository.updateDebitCardStatus(any(DebitCard.class)))
//                .thenReturn(resourceBundle.getString("status.update.success"));
//
//        // Create a sample DebitCard object
//        DebitCard debitCard = new DebitCard();
//        debitCard.setAccountNumber(1234567890L);
//        debitCard.setDebitCardStatus("block");
//
//        // Call updateStatus endpoint and verify response
//        ResponseEntity<String> responseEntity = updateStatus.updateStatus(debitCard);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(resourceBundle.getString("status.update.failed"), responseEntity.getBody());
//    }

    @Test
    @WithMockUser(username = "aru", password = "abc")
    void testUpdateSuccess() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 369246813579667,\n" +
                "  \"accountNumber\": 17896570987961,\n" +
                "  \"customerId\": 123671,\n" +
                "  \"debitCardCvv\": 234,\n" +
                "  \"debitCardPin\": 1000,\n" +
                "  \"debitCardExpiry\": \"2029-02-09\",\n" +
                "  \"debitCardStatus\": \"block\",\n" +
                "  \"domesticLimit\": 50000.0,\n" +
                "  \"internationalLimit\": 150000.0\n" +
                "}\n";

        // Mock repository response
        when(debitCardRepository.updateDebitCardStatus(any()))
                .thenReturn("Debit card status updated successfully");

        // Perform PUT request with request body
        mockMvc.perform(MockMvcRequestBuilders
                .put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());

    }



    @Test
    @WithMockUser(username = "aru", password = "abc")
    void testUpdateFailure() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 369246813579667,\n" +
                "  \"accountNumber\": 17896570987961,\n" +
                "  \"customerId\": 123671,\n" +
                "  \"debitCardCvv\": 234,\n" +
                "  \"debitCardPin\": 1000,\n" +
                "  \"debitCardExpiry\": \"2029-02-09\",\n" +
                "  \"debitCardStatus\": \"block\",\n" +
                "  \"domesticLimit\": 50000.0,\n" +
                "  \"internationalLimit\": 150000.0\n" +
                "}\n";

        // Mock repository response
        when(debitCardRepository.updateDebitCardStatus(any()))
                .thenReturn("Debit card status updated successfully");

        // Perform PUT request with request body
        mockMvc.perform(MockMvcRequestBuilders
                .put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());


    }

}
