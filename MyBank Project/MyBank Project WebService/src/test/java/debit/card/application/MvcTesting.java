package debit.card.application;


import debit.card.application.mvc.CardWebController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MvcTesting {
    @InjectMocks
    private CardWebController cardWebController;

    @Test
    public void testLandingPage() {
        String result = cardWebController.landing();
        assertEquals("index", result);
    }

    @Test
    public void testHomePage(){
        String result = cardWebController.homePage();
        assertEquals("dashboard",result);
    }

    @Test
    public void testViewCard(){
        String result = cardWebController.viewing();
        assertEquals("viewCard",result);
    }

    @Test
    public void testError(){
        String result = cardWebController.error();
        assertEquals("error",result);
    }


}
