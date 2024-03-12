package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.middleware.TransactionFileRepository;
import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

import java.util.List;

public class TransactionService {
    TransactionRepository transactionRepository;
    public TransactionService(){
        transactionRepository=new TransactionFileRepository("transaction.txt");
    }

    public boolean callVerifyAccount(String userName, String password) {
        try {
            return transactionRepository.verifyAccount(userName, password);
        } catch (Exception e) {

        }
        return false;
    }
    public void addAccount(){
        try{
            transactionRepository.addAccount();
        }catch (Exception e){

        }
    }

    public void callViewTransaction(String userName) {
        try{
            transactionRepository.viewTransaction(userName);
        }catch (Exception e){

        }
    }
    public TransactionService(StorageTarget storageTarget){
//        creditCardRepository=new CreditCardFileRepository("mybank-creditcard.doc");
        transactionRepository = storageTarget.getTransactionRepository();
    }

    public List<Account> callFindByDate(String startDate,String endDate){
        return transactionRepository.findByDate(startDate,endDate);
    }
    public List<Account> callFindByAmount(Double amount){
        return transactionRepository.findByAmount(amount);
    }
    public List<Account> callFindByType(String type){
        return transactionRepository.findByType(type);
    }

}
