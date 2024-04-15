package debit.card.application.rest;


import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.*;
import debits.cards.dao.remotes.DebitCardRepository;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
//@ComponentScan("debits.cards.dao.services")
@RestController
@RequestMapping("/update")
public class  UpdateStatusController {

    @Autowired
    private DebitCardRepository debitCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(UpdateStatusController.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DebitCard blocked successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to block"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(@Valid @RequestBody DebitCard debitCard) {
        try {
            String response = debitCardRepository.updateDebitCardStatus(debitCard);
            logger.info(resourceBundle.getString("status.update.success"));
            return ResponseEntity.ok(response);
        } catch (CustomerNotFoundException customerException) {
            logger.error(resourceBundle.getString("customer.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerException.getMessage());
        } catch (AccountNotFoundException accountException) {
            logger.error(resourceBundle.getString("account.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountException.getMessage());
        }
        catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("status.update.failed"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(debitCardException.getMessage());
        } catch (InternalServerException exception) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    //For Handling Bean Validation Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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


//    @PutMapping("/status")
//    public String statusUpdate(@RequestBody debits.cards.dao.entities.DebitCard debitCard) {
//        String message = "";
//        try {
//            message = debitCardRepository.updateDebitCardStatus(debitCard);
//            System.out.println(message);
//        } catch (DebitCardException cardException) {
//            logger.warn(cardException.toString());
//        }
//        return message;
//    }

}
