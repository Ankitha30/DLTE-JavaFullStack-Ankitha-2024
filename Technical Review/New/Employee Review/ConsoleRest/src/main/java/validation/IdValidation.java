package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class IdValidation {
    public static boolean isValidId(String id) {
        Logger logger = LoggerFactory.getLogger(PhoneNumberValidation.class);
//        int n = Integer.parseInt(id);
        String phoneRegex = "\\d+";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (pattern.matcher(id).matches())
            return true;
        else {
            logger.warn("Invalid id");

        }
        return false;

    }
}
