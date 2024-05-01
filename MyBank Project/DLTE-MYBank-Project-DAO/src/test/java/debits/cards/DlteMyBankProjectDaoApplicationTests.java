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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@SpringBootTest
class DlteMyBankProjectDaoApplicationTests {

    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DebitCardService debitCardRepository;


    @Test
    void testAllDebitCards() throws SQLException, DebitCardException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        DebitCard debitCard= new DebitCard(1234567890981234L,78903456789123L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);

        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);
        given(jdbcTemplate.query(eq("SELECT * FROM mybank_app_debitcard where debitCard_status=? or debitCard_status=?"),
       eq(new Object[]{"active", "inactive"}), any(DebitCardService.DebitCardMapper.class))).willReturn(debitCardList);


//        List<DebitCard> actualList = debitCardRepository .listAllCards();
//        System.out.println(actualList.size());
//        assertSame(debitCardList.size(),actualList.size());  // pass
//        assertNull(actualList.get(3)); // fail
//        assertNotSame(11111122343L,actualList.get(0).getCustomerId());
//        assertEquals(111,actualList.get(0).getDebitCardCvv()); //pass
    }

//    @Test
//    void testGetDebitCardThrowsException() throws SQLSyntaxErrorException{
//        // Mocking the exception thrown by jdbcTemplate
//        when(jdbcTemplate.query(anyString(), any(DebitCardService.DebitCardMapper.class)))
//                .thenReturn(new ArrayList<>())
//                .thenThrow(new DataAccessException(resourceBundle.getString("list.sql.error")) {});
//
//        // Assert that DebitCardException is thrown
//        assertThrows(DebitCardException.class, () -> debitCardRepository.listAllCards());
//
//    }

}
