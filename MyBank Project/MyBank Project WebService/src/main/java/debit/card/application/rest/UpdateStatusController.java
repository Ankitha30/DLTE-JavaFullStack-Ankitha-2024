package debit.card.application.rest;




import debits.cards.dao.entities.Customer;
import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.*;
import debits.cards.dao.remotes.DebitCardRepository;

import debits.cards.dao.services.CardSecurityService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@ComponentScan("debits.cards.dao.services")
@RestController
@RequestMapping("/update")
public class UpdateStatusController {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    CardSecurityService service;

    private static final Logger logger = LoggerFactory.getLogger(UpdateStatusController.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("cards");

    // for api docs
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DebitCard blocked successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to block"),

    })


    //  calls the method to update the card status.
    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(@Valid @RequestBody DebitCard debitCard) {

        String name = getUser();
        System.out.println(name);
        String username = service.getUserName(debitCard.getAccountNumber());
        if (!name.equals(username))
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("permission.denied"));
        try {
            Customer customer = service.findByUserName(name);

            debitCard.setCustomerId(customer.getCustomerId());
            logger.info(debitCard.getDebitCardNumber()+" "+debitCard.getDebitCardCvv()+" "+debitCard.getAccountNumber()+" "+debitCard.getDebitCardStatus()+" "+debitCard.getDebitCardExpiry()+" "+debitCard.getDomesticLimit()+" "+debitCard.getInternationalLimit());
            String response = debitCardRepository.updateDebitCardStatus(debitCard);
            logger.info(resourceBundle.getString("status.update.success"));
            return ResponseEntity.ok(response);
        }  catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("status.update.failed"));
            return ResponseEntity.status(HttpStatus.OK).body(debitCardException.getMessage());
        } catch (DataAccessException exception) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }
    }

    // rest controller to handle the exception due to ean validation
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/name")
    public String getCustomerName() {
        String name = getUser();
        String user = service.getCustomerName(name);
        return user;
    }


    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }




}
