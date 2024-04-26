package com.thymeleaf.rest.exception;

public class TransactionException extends RuntimeException{
    public TransactionException(){
        super(("Transaction not possible"));
    }
    public TransactionException(String info){
        super("Transaction not possible "+info);
    }
}
