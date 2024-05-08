package validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class EmailValidation {

    public static boolean isValidEmail(String email) {
        Logger logger = LoggerFactory.getLogger(EmailValidation.class);
        String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-z0-9._]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches())
            return true;
        else {
            logger.warn("Invalid Email");
//            System.out.println("Enter the valid email");
        }
        return false;
    }
}
