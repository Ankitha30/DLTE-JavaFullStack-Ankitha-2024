package debit.card.application;


import debit.card.application.configs.DebitCardPhase;
import debits.cards.dao.entities.Customer;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;
import debits.cards.dao.services.CardSecurityService;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SoapTesting {

    @InjectMocks
    private DebitCardPhase debitCardPhase;

    @Mock
    private DebitCardRepository debitCardServices;

    @Mock
    private CardSecurityService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewDebitCard() {
        List<DebitCard> list = new ArrayList<>();

        DebitCard card1 = new DebitCard();
        card1.setDebitCardNumber(1234567890123456L);
        card1.setAccountNumber(90876543212345L);
        card1.setCustomerId(12); // Update customerId to match the mocked customer
        card1.setDebitCardCvv(123456);
        card1.setDebitCardPin(342);
        card1.setDebitCardExpiry(new Date(128, 2, 3));
        card1.setDebitCardStatus("active");
        card1.setDomesticLimit(50000);
        card1.setInternationalLimit(80000);
        list.add(card1);

        // Mock Authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("aru");

        // Mock Customer
        Customer customer = new Customer();
        customer.setCustomerId(12);
        customer.setCustomerName("Arundhathi");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("aru");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(service.findByUserName("aru")).thenReturn(customer);

        when(debitCardServices.listAllCards(12)).thenReturn(list); // Update customerId here

        ViewDebitCardResponse response = debitCardPhase.viewDebitCard(new ViewDebitCardRequest());

        assertEquals(1, response.getDebitCard().size());
        assertEquals(12,response.getDebitCard().get(0).getCustomerId());

        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
    }


    @Test
    @WithMockUser(username = "aru")
    public void testViewDebitCardResponse_DebitCardException() throws DebitCardException {

        List<DebitCard> list = new ArrayList<>();
        DebitCard card1 = new DebitCard();
        card1.setDebitCardNumber(1234567890123456L);
        card1.setAccountNumber(90876543212345L);
        card1.setCustomerId(12); // Update customerId to match the mocked customer
        card1.setDebitCardCvv(123456);
        card1.setDebitCardPin(342);
        card1.setDebitCardExpiry(new Date(128, 2, 3));
        card1.setDebitCardStatus("active");
        card1.setDomesticLimit(50000);
        card1.setInternationalLimit(80000);
        list.add(card1);

        // Mock Authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("aru");

        // Mock Customer
        Customer customer = new Customer();
        customer.setCustomerId(12);
        customer.setCustomerName("Arundhathi");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("aru");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(service.findByUserName("aru")).thenReturn(customer);

        when(debitCardServices.listAllCards(12)).thenThrow(DebitCardException.class);

        // Create a mock ViewDebitCardRequest
        ViewDebitCardRequest request = new ViewDebitCardRequest();

        ViewDebitCardResponse response = debitCardPhase.viewDebitCard(request);

        // Assertions
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
    }

























}
