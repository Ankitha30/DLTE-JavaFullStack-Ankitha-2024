package debit.card.application;


import debit.card.application.security.CardFailure;
import debit.card.application.security.CardSecurityApi;
import debit.card.application.security.CardSuccessHandler;
import debits.cards.dao.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SecurityTesting {

    @Mock
    debits.cards.dao.services.CardSecurityService cardSecurityServices;


    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    CardSuccessHandler cardSuccessHandler;
    @InjectMocks
    CardFailure cardFailureHandler;
    @InjectMocks
    CardSecurityApi cardSecurityApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void onAuthenticationSuccess_testAttempts() throws ServletException, IOException {
        Customer customer = new Customer();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        customer.setCustomerStatus("active");
        customer.setAttempts(1);
        when(authentication.getPrincipal()).thenReturn(customer);
        cardSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        assertEquals("/web/dash", response.getRedirectedUrl());
    }
@Test
    void onAuthenticationSuccess_testAttemptsExceeded() throws ServletException, IOException {
        Customer customer = new Customer();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        customer.setCustomerStatus("block");
        when(authentication.getPrincipal()).thenReturn(customer);
        cardSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        assertEquals("/web/?errors=Contact admin", response.getRedirectedUrl());
    }



    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setUsername("aru");
        customer.setPassword("abc");

        when(passwordEncoder.encode("abc")).thenReturn("encodedPassword");
        when(cardSecurityServices.signingUp(customer)).thenReturn(customer);
        Customer result = cardSecurityApi.save(customer);
        verify(passwordEncoder).encode("abc");
        verify(cardSecurityServices).signingUp(customer);

        assertEquals(customer, result);
    }


    @Test
    public void testAuthenticationFailureUserNotExists() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new UsernameNotFoundException("User not exists");

        String username = "nonExistingUser";
        when(cardSecurityServices.findByUserName(username)).thenReturn(null);

        cardFailureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/web/?error=User not found", response.getRedirectedUrl());
    }

}
