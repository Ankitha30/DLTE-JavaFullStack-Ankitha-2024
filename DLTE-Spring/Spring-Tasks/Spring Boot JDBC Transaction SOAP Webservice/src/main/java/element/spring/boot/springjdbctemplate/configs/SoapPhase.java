package element.spring.boot.springjdbctemplate.configs;

import element.spring.boot.springjdbctemplate.TransactionService;
import element.spring.boot.springjdbctemplate.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import transaction.services.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url="http://services.transaction";
    @Autowired
    private TransactionService transactionServices;

    @PayloadRoot(namespace = url,localPart = "newTransactionRequest")//local part specifies what is it handling
    @ResponsePayload
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse=new NewTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        transaction.services.Transactions actualTransaction=newTransactionRequest.getTransaction();
        Date date = newTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        Transaction daoTransaction=new Transaction();
        daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actualTransaction,daoTransaction);

        daoTransaction=transactionServices.apiSave(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+"is inserted");
        }else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+"not inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        BeanUtils.copyProperties(daoTransaction,actualTransaction);
        newTransactionResponse.setTransactions(actualTransaction);

        return newTransactionResponse;
    }
    @PayloadRoot(namespace = url,localPart = "filterBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterBySender(@RequestPayload FilterBySenderRequest filterBySenderRequest){
        FilterBySenderResponse filterBySenderResponse=new FilterBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<transaction.services.Transactions> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindBySender(filterBySenderRequest.getTransactionBy());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            transaction.services.Transactions currentTransaction=new transaction.services.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by sender "+filterBySenderRequest.getTransactionBy()+" is fetched");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransaction().addAll(transactions);
        return filterBySenderResponse;

    }

    @PayloadRoot(namespace = url,localPart = "filterByReceiverRequest")
    @ResponsePayload
    public FilterByReceiverResponse filterByReceiver(@RequestPayload FilterByReceiverRequest filterByReceiverRequest){
        FilterByReceiverResponse filterByReceiverResponse=new FilterByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<transaction.services.Transactions> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindByReceiver(filterByReceiverRequest.getTransactionTo());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            transaction.services.Transactions currentTransaction=new transaction.services.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by receiver "+filterByReceiverRequest.getTransactionTo()+" is fetched");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransaction().addAll(transactions);
        return filterByReceiverResponse;

    }
    @PayloadRoot(namespace = url,localPart = "filterByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterByAmount(@RequestPayload FilterByAmountRequest filterByAmountRequest){
        FilterByAmountResponse filterByAmountResponse=new FilterByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<transaction.services.Transactions> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindByAmount(filterByAmountRequest.getTransactionAmount());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            transaction.services.Transactions currentTransaction=new transaction.services.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by amount "+filterByAmountRequest.getTransactionAmount()+" is fetched");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransaction().addAll(transactions);
        return filterByAmountResponse;
    }

    @PayloadRoot(namespace = url,localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarksResponse updateByRemarks(@RequestPayload UpdateRemarksRequest updateByRemarksRequest){
        UpdateRemarksResponse updateByRemarksResponse=new UpdateRemarksResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        transaction.services.Transactions transaction=new transaction.services.Transactions();

        Transaction daoTransaction=new Transaction();
        BeanUtils.copyProperties(updateByRemarksRequest.getTransaction(),daoTransaction);
        daoTransaction=transactionServices.apiUpdateTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Transaction updated");
        }else
        {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Transaction update failed");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);
        updateByRemarksResponse.setServiceStatus(serviceStatus);
        updateByRemarksResponse.setTransaction(transaction);
        return updateByRemarksResponse;
    }

    @PayloadRoot(namespace = url,localPart = "deleteByRangeOfTransactionRequest")
    @ResponsePayload
    public DeleteByRangeOfTransactionResponse deleteBasedOnDates(@RequestPayload DeleteByRangeOfTransactionRequest deleteByRangeOfDatesRequest){
        DeleteByRangeOfTransactionResponse deleteByRangeOfDatesResponse=new DeleteByRangeOfTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        Date startDate=deleteByRangeOfDatesRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate=deleteByRangeOfDatesRequest.getEndDate().toGregorianCalendar().getTime();
        String deleteTransaction=transactionServices.apiDeleteTransaction(startDate,endDate);

        if(deleteTransaction.contains("deleted")){
            serviceStatus.setStatus("FAILURE");
        }else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(deleteTransaction);
        deleteByRangeOfDatesResponse.setServiceStatus(serviceStatus);
        return deleteByRangeOfDatesResponse;
    }

}
