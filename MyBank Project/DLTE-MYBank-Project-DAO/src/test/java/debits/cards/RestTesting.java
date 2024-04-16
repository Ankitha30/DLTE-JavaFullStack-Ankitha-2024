package debits.cards;


import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.CustomerNotFoundException;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestTesting {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DebitCardService debitCardRepository;

    @Test
    void testUpdateStatus_Success() {
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_status", "SQLCODE-000");
        when(jdbcTemplate.call(any(CallableStatementCreator.class), any())).thenReturn(returnedExecution);
        DebitCard debitCardToUpdate = new DebitCard(3692468135796673L, 17896570934897L, 200005, 122, 2112, new Date(2027, 10, 10), "block", 200000.0, 5000000.0);
        String result = debitCardRepository.updateDebitCardStatus(debitCardToUpdate);
        assertEquals("Debit card Blocked Successfully", result);

    }

    @Test
    void testUpdateDebitCardStatus_CustomerNotFound() {
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_status", "SQLCODE-002");
        when(jdbcTemplate.call(any(CallableStatementCreator.class), any()))
                .thenReturn(returnedExecution);
        DebitCard debitCard = new DebitCard(3692468135796673L, 17896570934897L, 200005, 122, 2112, new Date(2027, 10, 10), "active", 200000.0, 5000000.0);

        // Verify that CustomerNotFoundException is thrown
        assertThrows(CustomerNotFoundException.class, () -> {
            debitCardRepository.updateDebitCardStatus(debitCard);
        });
    }


}



