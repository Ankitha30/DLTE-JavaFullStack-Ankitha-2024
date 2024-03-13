package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

import java.util.List;
import java.util.ResourceBundle;

public class TransactionService {
    TransactionRepository transactionRepository;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");

    public TransactionService(StorageTarget storageTarget){
        transactionRepository=storageTarget.getTransactionRepository();
    }
    public boolean callverifyAccount(String userName, String password) {
        try {
            return transactionRepository.verifyAccount(userName, password);
        } catch (Exception e) {

        }
        return false;
    }
//    public void addAccount(){
//        try{
//            transactionRepository.addAccount();
//        }catch (Exception e){
//
//        }
//    }

    public void callViewTransaction(String userName) {
        try{
            transactionRepository.viewTransaction(userName);
        }catch (Exception e){

        }
    }

    public List<Transaction> callFindByDate(String startDate, String endDate, String userName){
        return transactionRepository.findByDate(startDate,endDate);
    }
    public List<Transaction> callFindByAmount(Double amount){
        return transactionRepository.findByAmount(amount);
    }
    public List<Transaction> callFindByType(String type){
        return transactionRepository.findByType(type);
    }

}


 
