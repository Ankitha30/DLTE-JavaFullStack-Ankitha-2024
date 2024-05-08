package org.exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
    public ConnectionException() {
        super("System failure");
    }

}
