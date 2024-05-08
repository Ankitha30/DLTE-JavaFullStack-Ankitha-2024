package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class PinCodeValidation {
    public static boolean isValidPin(String phone) {
        Logger logger = LoggerFactory.getLogger(PinCodeValidation.class);
        String phoneRegex = "\\d{6}";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (pattern.matcher(phone).matches())
            return true;
        else {
            logger.error("Invalid PIN code");
//            System.out.println("Enter the valid pincode");
        }
        return false;

    }
}
