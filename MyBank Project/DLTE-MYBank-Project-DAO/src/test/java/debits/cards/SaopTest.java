package debits.cards;


import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Date;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaopTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    DebitCardService service;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");




    @Test
    public void listAllTestException() throws SQLSyntaxErrorException {
        // Mock jdbcTemplate.query() to return an empty list
        DebitCard card1 = new DebitCard();
        card1.setDebitCardNumber(1234567890123456L);
        card1.setAccountNumber(90876543212345L);
        card1.setCustomerId(123456);
        card1.setDebitCardCvv(123456);
        card1.setDebitCardPin(342);
        card1.setDebitCardExpiry(new Date(128, 2, 3));
        card1.setDebitCardStatus("block");
        card1.setDomesticLimit(50000);
        card1.setInternationalLimit(80000);

        DebitCard card2 = new DebitCard();
        card2.setDebitCardNumber(1234567890123458L);
        card2.setAccountNumber(79876543212345L);
        card2.setCustomerId(823456);
        card2.setDebitCardCvv(823456);
        card2.setDebitCardPin(343);
        card2.setDebitCardExpiry(new Date(128, 2, 3));
        card2.setDebitCardStatus("block");
        card2.setDomesticLimit(50000);
        card2.setInternationalLimit(80000);

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class)))
                .thenReturn(Collections.emptyList());

        // Call the method under test with a sample customer ID and capture the exception
        DebitCardException exception = assertThrows(DebitCardException.class, () -> {
            service.listAllCards(123456);
        });

        // Verify the exception message
        assertEquals("data not found", exception.getMessage());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class));
    }



    @Test
    public void listTestActive() throws SQLSyntaxErrorException {
        // Mock jdbcTemplate.query() to return an empty list
        DebitCard card1 = new DebitCard();
        card1.setDebitCardNumber(1234567890123456L);
        card1.setAccountNumber(90876543212345L);
        card1.setCustomerId(123456);
        card1.setDebitCardCvv(123456);
        card1.setDebitCardPin(342);
        card1.setDebitCardExpiry(new Date(128, 2, 3));
        card1.setDebitCardStatus("active");
        card1.setDomesticLimit(50000);
        card1.setInternationalLimit(80000);

        DebitCard card2 = new DebitCard();
        card2.setDebitCardNumber(1234567890123458L);
        card2.setAccountNumber(79876543212345L);
        card2.setCustomerId(823456);
        card2.setDebitCardCvv(823456);
        card2.setDebitCardPin(343);
        card2.setDebitCardExpiry(new Date(128, 2, 3));
        card2.setDebitCardStatus("block");
        card2.setDomesticLimit(50000);
        card2.setInternationalLimit(80000);

        List<DebitCard> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardService.DebitCardMapper.class)))
                .thenReturn(list);

        // Call the method under test with a sample customer ID and capture the exception
        List<DebitCard> result = service.listAllCards(123456);

        // Verify the exception message
        assertEquals(2, result.size());


    }

    @Test
    public void testListAllCards() throws SQLException {
        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(1)).thenReturn(1234567890123456L);
        when(resultSet.getLong(2)).thenReturn(90876543212345L);
        when(resultSet.getInt(3)).thenReturn(123456);
        when(resultSet.getInt(4)).thenReturn(342);
        when(resultSet.getInt(5)).thenReturn(2312);
        when(resultSet.getDate(6)).thenReturn(new Date(2024,2,3));
        when(resultSet.getString(7)).thenReturn("active");
        when(resultSet.getDouble(8)).thenReturn(50000.0);
        when(resultSet.getDouble(9)).thenReturn(80000.0);

        // Mock RowMapper
        DebitCardService.DebitCardMapper mapper = service.new DebitCardMapper();
        DebitCard debitCard = mapper.mapRow(resultSet, 1);

        // Assertions
        assertEquals(1234567890123456L, debitCard.getDebitCardNumber());
        assertEquals(90876543212345L, debitCard.getAccountNumber());
        assertEquals(123456, debitCard.getCustomerId());
        assertEquals(342, debitCard.getDebitCardCvv());
        assertEquals(2312, debitCard.getDebitCardPin());
        assertEquals(new Date(2024, 2, 3), debitCard.getDebitCardExpiry());
        assertEquals("active", debitCard.getDebitCardStatus());
        assertEquals(50000.0, debitCard.getDomesticLimit());
        assertEquals(80000.0, debitCard.getInternationalLimit());
    }



}