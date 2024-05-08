package com.jdbc.springcomponent.exceptions;

public class EmployeeException extends RuntimeException {
//    public EmployeeException(String insertion_failed) {
//    }

    public EmployeeException(){
        super("Credit Card Not available");
    }
    public EmployeeException(String info){
        super("Credit Card Not available "+info);
    }


}
