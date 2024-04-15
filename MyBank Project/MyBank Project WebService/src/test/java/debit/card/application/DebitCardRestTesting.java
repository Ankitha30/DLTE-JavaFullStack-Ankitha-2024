package debit.card.application;

import debit.card.application.configs.DebitCardPhase;
import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DebitCardRestTesting {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");


    @MockBean
    private DebitCardService debitCardRepository;



    @InjectMocks
    private UpdateStatusController updateStatus;



        @Test
    void testUpdateStatus_Success() {
        // Mock successful response from DebitCardRepository
        when(debitCardRepository.updateDebitCardStatus(any(DebitCard.class)))
                .thenReturn(resourceBundle.getString("status.update.success"));

        // Create a sample DebitCard object
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardStatus("block");

        // Call updateStatus endpoint and verify response
        ResponseEntity<String> responseEntity = updateStatus.updateStatus(debitCard);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resourceBundle.getString("status.update.success"), responseEntity.getBody());
    }
 @Test
    void testUpdateStatus_Success1() {
        // Mock successful response from DebitCardRepository
        when(debitCardRepository.updateDebitCardStatus(any(DebitCard.class)))
                .thenReturn(resourceBundle.getString("status.update.success"));

        // Create a sample DebitCard object
        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(1234567890L);
        debitCard.setDebitCardStatus("block");

        // Call updateStatus endpoint and verify response
        ResponseEntity<String> responseEntity = updateStatus.updateStatus(debitCard);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resourceBundle.getString("status.update.failed"), responseEntity.getBody());
    }
//@Test
//    void testUpdateStatus_Failure() {
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
//        assertEquals(resourceBundle.getString("status.update.success"), responseEntity.getBody());
//    }


}
