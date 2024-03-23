package spring.boot.jdbc.springbootjdbc.exception;

public class TransactionException extends RuntimeException{
    public TransactionException(){
        super(("Transaction not possible"));
    }
    public TransactionException(String info){
        super("Transaction not possible "+info);
    }
}
/*
public CardException(){
        super("Credit Card Not available");
    }
    public CardException(String info){
        super("Credit Card Not available "+info);
    }
 */