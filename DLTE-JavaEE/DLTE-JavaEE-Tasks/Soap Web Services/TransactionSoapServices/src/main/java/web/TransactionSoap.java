package web;


import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;


//        findByUsername>> Read service of finding account(can be useful for login)
//        findAllByUsername> Read service of finding transactions of respective username
//        findAllByDateAndUsername>> Read service of finding transactions of respective username's by given start and end date
//        findAllByTypeAndUsername>> Read Service finding transactions of respective user's transaction type
//        Perform all these features based on team:
//
//        Wealth warriors - View Transaction:
//        features: 1, 2, 3, 4, 5
//*/

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionSoap {
    private TransactionService service;
    public TransactionSoap() {
        StorageTarget storageTarget=new DatabaseTarget();
        service = new TransactionService(storageTarget);
    }
    //createAccount  >> Create service of creating account
    @WebMethod
    public void createAccount(@WebParam (name="CreateAccount") Account account){
        service.callSaveAccount(account);
    }

    //        findByUsername>> Read service of finding account(can be useful for login)
    @WebMethod
    @WebResult(name="GroupOfTransactions")
    public boolean findByUsername(@WebParam(name="User") String user,@WebParam(name="Password") String password){
        Boolean transactionList=service.verifyAccount(user,password);
        return transactionList;
    }

//        findAllByUsername> Read service of finding transactions of respective username
    @WebMethod
    @WebResult(name="TransactionUser")
    public  GroupOfServices readUser(@WebParam(name="User") String UserName  )
    {
        GroupOfServices groupOfServices= new GroupOfServices();
        List<Transaction> transactionServiceList = service.callViewTransaction(UserName);
        groupOfServices.setTransaction(transactionServiceList);
        return groupOfServices;
    }
//        findAllByDateAndUsername>> Read service of finding transactions of respective username's by given start and end date

    @WebMethod
    @WebResult(name="UserAndDate")
    public GroupOfServices readAllDateByUser(@WebParam(name="User") String user,@WebParam(name="StartDate") String startDate, @WebParam(name="EndDate") String endDate){
        GroupOfServices groupOfTransactions=new GroupOfServices();
        List<Transaction> transactionList=service.callFindByDate(user,startDate,endDate);
        groupOfTransactions.setTransaction(transactionList);
        return groupOfTransactions;
    }

    //        findAllByTypeAndUsername>> Read Service finding transactions of respective user's transaction type
    @WebMethod
    @WebResult(name="TransactionUserAndType")
    public  GroupOfServices readAll(@WebParam(name="User") String UserName,@WebParam(name="TransactionType") String TransactionType  )
    {
        GroupOfServices groupOfServices= new GroupOfServices();
        List<Transaction> transactionServiceList = service.callFindByType(UserName,TransactionType);
        groupOfServices.setTransaction(transactionServiceList);
        return groupOfServices;
    }



}
