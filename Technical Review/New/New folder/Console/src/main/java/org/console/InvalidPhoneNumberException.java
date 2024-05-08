package org.console;

public class InvalidPhoneNumberException extends Throwable {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
