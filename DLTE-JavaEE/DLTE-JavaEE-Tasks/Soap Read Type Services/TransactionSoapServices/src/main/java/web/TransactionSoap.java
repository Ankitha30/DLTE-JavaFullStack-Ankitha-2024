package web;


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

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionSoap {
    private TransactionService service;
    public TransactionSoap() {
        StorageTarget storageTarget=new DatabaseTarget();
        service = new TransactionService(storageTarget);
    }

    @WebMethod
    @WebResult(name="TransactionUserAndType")
    public  GroupOfServices readAll(@WebParam(name="StringUser") String UserName,@WebParam(name="StringTransactionType") String TransactionType  )
    {
        GroupOfServices groupOfServices= new GroupOfServices();
        List<Transaction> transactionServiceList = service.callFindByType(UserName,TransactionType);
        groupOfServices.setTransaction(transactionServiceList);
        return groupOfServices;
    }

}
