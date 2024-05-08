package com.jdbc.springcomponent.exceptions;

public class EmployeeException extends RuntimeException {
//    public EmployeeException(String insertion_failed) {
//    }

    public EmployeeException(){
        super("Employee Details Not available");
    }
    public EmployeeException(String info){
        super("Employee Details Not available "+info);
    }


}
