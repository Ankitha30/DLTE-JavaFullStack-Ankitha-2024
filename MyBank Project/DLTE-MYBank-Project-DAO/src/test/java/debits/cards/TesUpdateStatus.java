package debits.cards;

import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TesUpdateStatus {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    DebitCardService debitCardService;

     ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Test
    public void testUpdateDebitCardStatus_Success() throws SQLException {
        // Mock the behavior of jdbcTemplate.call() to return a success message

        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(123456789L);
        debitCard.setDebitCardStatus("Block");
        debitCard.setDebitCardPin(1234);

        CallableStatement callableStatement = Mockito.mock(CallableStatement.class);
        when(callableStatement.getObject(4)).thenReturn("SQLCODE-000");

        Connection connection = Mockito.mock(Connection.class);
        when(connection.prepareCall(any(String.class))).thenReturn(callableStatement);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenAnswer(invocation -> {
            CallableStatementCreator creator = invocation.getArgument(0);
            creator.createCallableStatement(connection);
            return new HashMap<String, Object>() {{
                put("p_status", "SQLCODE-000");
            }};
        });
//
        String result = debitCardService.updateDebitCardStatus(debitCard);
        assertEquals(resourceBundle.getString("status.update.success"), result);

    }


    @Test
    void testUpdate() throws SQLException {


        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(123456789L);
        debitCard.setDebitCardStatus("Block");
        debitCard.setDebitCardPin(1234);

        CallableStatement callableStatement = Mockito.mock(CallableStatement.class);
        when(callableStatement.getObject(4)).thenReturn("SQLCODE-003");

        Connection connection = Mockito.mock(Connection.class);
        when(connection.prepareCall(any(String.class))).thenReturn(callableStatement);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenAnswer(invocation -> {
            CallableStatementCreator creator = invocation.getArgument(0);
            creator.createCallableStatement(connection);
            return new HashMap<String, Object>() {{
                put("p_status", "SQLCODE-003");
            }};
        });
//

        when(callableStatement.getObject(4)).thenReturn("SQLCODE-003");
        assertThrows(DebitCardException.class, () -> debitCardService.updateDebitCardStatus(debitCard));

    }


    @Test
    void testUpdateDebitCardStatus_SQLCode005_InternalError() throws SQLException {
        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(123456789L);
        debitCard.setDebitCardStatus("Active");
        debitCard.setDebitCardPin(1234);

        CallableStatement callableStatement = Mockito.mock(CallableStatement.class);
        when(callableStatement.getObject(4)).thenReturn("SQLCODE-005");

        Connection connection = Mockito.mock(Connection.class);
        when(connection.prepareCall(any(String.class))).thenReturn(callableStatement);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenAnswer(invocation -> {
            CallableStatementCreator creator = invocation.getArgument(0);
            creator.createCallableStatement(connection);
            return Collections.singletonMap("p_status", "SQLCODE-005");
        });

        assertThrows(DebitCardException.class, () -> debitCardService.updateDebitCardStatus(debitCard));
    }

    @Test
    void testUpdateDebitCardStatus_DataAccessException() {
        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(1234567890L);
        debitCard.setDebitCardStatus("ACTIVE");
        debitCard.setDebitCardPin(1234);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenThrow(new DataAccessException("Data access exception") {
                });

        assertThrows(DebitCardException.class, () -> debitCardService.updateDebitCardStatus(debitCard));
    }

}
