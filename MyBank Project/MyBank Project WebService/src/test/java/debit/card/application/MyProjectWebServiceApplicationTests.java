package debit.card.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import debit.card.application.configs.DebitCardPhase;
import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.services.DebitCardService;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MyProjectWebServiceApplicationTests {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("cards");
    private MockMvc mockMvc;

    @MockBean
    private DebitCardService debitCardRepository;

    @InjectMocks
    private DebitCardPhase debitCardPhase;

    @InjectMocks
    private UpdateStatusController updateStatus;
//    @Test
//    void testAllDebitCards_success() throws SQLException, DebitCardException {
//        // Mocking the response from the database
////        List<DebitCard> debitCardList = new ArrayList<>();
////
////        DebitCard debitCard= new DebitCard(12345678990232L,78903456782902L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
////        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
////
////        debitCardList = Stream.of(debitCard,debitCard1).collect(Collectors.toList());
////        when(jdbcTemplate.query(anyString(),any(DebitCardService.DebitCardMapper.class))).thenReturn(debitCardList);
////        given(jdbcTemplate.query(eq("SELECT * FROM mybank_app_debitcard where debitCard_status=? or debitCard_status=?"),
////                eq(new Object[]{"active", "inactive"}), any(DebitCardService.DebitCardMapper.class))).willReturn(debitCardList);
////
////
////        List<DebitCard> actualList = debitCardRepository.listAllCards();
////        System.out.println(actualList.size());
//////        assertEquals(2000.0,actualList.get(0).getDomesticLimit()); //pass
////        assertNotSame(2000.0,actualList.get(0).getDomesticLimit());
//    }
//
//    @Test
//    void testAllDebitCards() throws SQLException, DebitCardException {
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//        DebitCard debitCard= new DebitCard(12345678990232L,78903456782902L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
//
//        debitCardList = Stream.of(debitCard,debitCard1).collect(Collectors.toList());
//        debitCardList.forEach(System.out::println);
//        when(debitCardRepository.listAllCards()).thenReturn(debitCardList);
//        ViewDebitCardRequest request=new ViewDebitCardRequest();
//        ViewDebitCardResponse response= debitCardPhase.viewDebitCardResponse(request);
//            assertNotNull(response);
//        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getServiceStatus().getStatus()); //FAIL
//        assertEquals(HttpServletResponse.SC_OK,response.getServiceStatus().getStatus()); // pass
//        assertSame(4,response.getDebitCard().size());//fail
//        assertSame(2,response.getDebitCard().size());//pass
//    }
//
//    @Test
//    void testAllDebitCards_failure() throws SQLException, DebitCardException {
//        // Mocking the response from the database
////        List<DebitCard> debitCardList = new ArrayList<>();
////
////        DebitCard debitCard= new DebitCard(12345678990232L,78903456782902L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
////        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
////
////        debitCardList = Stream.of(debitCard,debitCard1).collect(Collectors.toList());
////        debitCardList.forEach(System.out::println);
////        when(debitCardRepository.listAllCards()).thenReturn(debitCardList);
////        ViewDebitCardRequest request=new ViewDebitCardRequest();
////        ViewDebitCardResponse response= debitCardPhase.viewDebitCardResponse(request);
////        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getServiceStatus().getStatus()); //FAIL
////
////        assertSame(4,response.getDebitCard().size());//fail
//
//    }


}
