package org.exceptions;

import java.util.ResourceBundle;

public class EmployeeException extends Exception {

    public EmployeeException(String message) {
        super(message);
    }

//    String msg;
//    public EmployeeException(String err){
//        this.msg=err;
//    }
//    public String getMsg(){
//        ResourceBundle resourceBundle= ResourceBundle.getBundle("database");
//        return resourceBundle.getString(msg);
//    }

}
