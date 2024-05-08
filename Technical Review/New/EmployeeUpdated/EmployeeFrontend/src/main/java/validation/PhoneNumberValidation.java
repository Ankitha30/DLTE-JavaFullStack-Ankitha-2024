package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class PhoneNumberValidation {
   public static boolean isValidPhone(String phone){
       Logger logger= LoggerFactory.getLogger(EmailValidation.class);
       String phoneRegex= "\\d{10}";
       Pattern pattern = Pattern.compile(phoneRegex);
       if(pattern.matcher(phone).matches())
           return true;
       else {
           logger.warn("Invalid Phone Number");
           System.out.println("Enter the valid mobile number");
       }
       return false;

   }
}
