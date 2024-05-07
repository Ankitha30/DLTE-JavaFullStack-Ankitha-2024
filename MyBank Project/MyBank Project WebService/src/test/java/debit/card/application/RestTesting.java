package debit.card.application;


import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.entities.Customer;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;
import debits.cards.dao.services.CardSecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RestTesting {

    @Mock
    private DebitCardRepository debitCardRepository;

    @Mock
    private CardSecurityService cardSecurityService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UpdateStatusController updateStatusController;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("cards");
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        mockMvc = MockMvcBuilders.standaloneSetup(updateStatusController).build();
    }



    @Test
    void testUpdateStatus_Success() throws DebitCardException {
        // Arrange
        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(123456789);

        Customer customer = new Customer();
        customer.setCustomerId(1);
        when(authentication.getName()).thenReturn("username");
        when(cardSecurityService.getUserName(123456789)).thenReturn("username");
        when(cardSecurityService.findByUserName("username")).thenReturn(customer);
        when(debitCardRepository.updateDebitCardStatus(debitCard)).thenReturn(resourceBundle.getString("status.update.success"));

        // Act
        ResponseEntity<String> response = updateStatusController.updateStatus(debitCard);

        // Assert
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals(resourceBundle.getString("status.update.success"));
    }

    @Test
    void testUpdateStatus_Fail() throws DebitCardException {
        // Arrange
        DebitCard debitCard = new DebitCard();
        debitCard.setAccountNumber(123456789);

        when(authentication.getName()).thenReturn("anu");
        when(cardSecurityService.getUserName(123456789)).thenReturn("anu");
        when(cardSecurityService.findByUserName("anu")).thenThrow(new DebitCardException(resourceBundle.getString("status.update.failed")));

        ResponseEntity<String> response = updateStatusController.updateStatus(debitCard);

        // Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        System.out.println(response.getBody());
//        assert response.getBody().equals(resourceBundle.getString("status.update.failed"));
    }


    @Test
    @WithMockUser(username = "testUser")
    void testUpdateStatus_Failure() throws Exception {
        String requestBody = "{\"debitCardNumber\": \"invalid\"," +
                "\"accountNumber\": 78909876543530," +
                "\"customerId\": 123670," +
                "\"debitCardCvv\": 123," +
                "\"debitCardPin\": 1234," +
                "\"debitCardExpiry\": \"2025-04-30\"," +
                "\"debitCardStatus\": \"active\"," +
                "\"domesticLimit\": 1000," +
                "\"internationalLimit\": 5}";



        mockMvc.perform(put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                // Verify that the response status is OK (200)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "aru", password = "abc")
    void testSQLException() throws Exception {
        DebitCard debitCard = new DebitCard();

        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";


        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("aru");

        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("aru");
        customer.setPassword("abc");
        customer.setAttempts(1);

        // Fix the stubbing to match the actual invocation
        when(cardSecurityService.findByUserName("aru")).thenReturn(customer);
        when(cardSecurityService.getUserName(anyLong())).thenReturn(customer.getUsername());

        // Stub the repository method to throw an exception
        when(debitCardRepository.updateDebitCardStatus(any()))
                .thenThrow(new DataAccessException("Internal Server Error Occurred") {});

        mockMvc.perform(put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string("Internal Server Error Occurred"));
    }


    @Test
    @WithMockUser(username = "testUser")
    void updateStatus_Success() throws Exception {
        DebitCard debitCard = new DebitCard();
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";
        debitCard.setAccountNumber(78909876543530L);
        debitCard.setDebitCardStatus("block");

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(cardSecurityService.findByUserName("testUser")).thenReturn(customer);
        when(cardSecurityService.getUserName(debitCard.getAccountNumber())).thenReturn(customer.getUsername());
        when(debitCardRepository.updateDebitCardStatus(any(DebitCard.class))).thenReturn("Success");



        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andReturn();
        verify(debitCardRepository, times(1)).updateDebitCardStatus(any(DebitCard.class));


    }


    @Test
    @WithMockUser(username = "testUser")
    void updateStatus_Failure() throws Exception {
        DebitCard debitCard = new DebitCard();
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"block\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";
        debitCard.setAccountNumber(78909876543530L);
        debitCard.setDebitCardStatus("block");
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);

        when(cardSecurityService.findByUserName("testUser")).thenReturn(customer);
        when(cardSecurityService.getUserName(debitCard.getAccountNumber())).thenReturn(customer.getUsername());
        when(debitCardRepository.updateDebitCardStatus(any(DebitCard.class)))
                .thenThrow(new DebitCardException("Failed"));

        mockMvc = MockMvcBuilders.standaloneSetup(updateStatusController).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andReturn();

        verify(debitCardRepository, times(1)).updateDebitCardStatus(any(DebitCard.class));
    }


    @Test
    public void testGetCustomerName() {
        // Mock SecurityContextHolder to return a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testUser");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(cardSecurityService.getCustomerName(Mockito.anyString())).thenReturn("Aru");

        String result = updateStatusController.getCustomerName();
        Assertions.assertEquals("Aru", result);
    }


    @Test
    @WithMockUser(username = "aru", password = "abc")
    void testHandleBeanValidationException() throws Exception {
        // Create a request with invalid data
        String request = "{\n" +
                "  \"debitCardNumber\": null,\n" +
                "  \"accountNumber\": null,\n" +
                "  \"customerId\": null,\n" +
                "  \"debitCardCvv\": null,\n" +
                "  \"debitCardPin\": null,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"null\",\n" +
                "  \"domesticLimit\": null,\n" +
                "  \"internationalLimit\": null\n" +
                "}";

        // Perform the request
        mockMvc.perform(put("/update/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }





}