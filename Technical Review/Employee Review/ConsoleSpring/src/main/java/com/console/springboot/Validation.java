package com.console.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class Validation {
   static  Logger logger = LoggerFactory.getLogger(Validation.class);
    public static boolean isValidEmail(String email) {

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
    public static boolean isValidId(String id) {

        String phoneRegex = "\\d";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (pattern.matcher(id).matches())
            return true;
        else {
            logger.warn("Invalid id");

        }
        return false;

    }

    public static boolean isValidPhone(String phone) {

        String phoneRegex = "\\d{10}";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (pattern.matcher(phone).matches())
            return true;
        else {
            logger.warn("Invalid Phone Number");

        }
        return false;

    }

    public static boolean isValidPin(String phone) {

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
