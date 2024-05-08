package org.console;

public class InvalidPinCodeException extends Throwable {
    public InvalidPinCodeException(String message) {
        super(message);
    }
}
