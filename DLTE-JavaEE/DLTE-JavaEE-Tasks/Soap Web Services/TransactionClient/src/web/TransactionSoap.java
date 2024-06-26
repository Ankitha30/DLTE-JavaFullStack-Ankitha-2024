
package web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TransactionSoap", targetNamespace = "http://web/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TransactionSoap {


    /**
     * 
     * @param transactionType
     * @param user
     * @return
     *     returns web.GroupOfServices
     */
    @WebMethod
    @WebResult(name = "TransactionUserAndType", partName = "TransactionUserAndType")
    @Action(input = "http://web/TransactionSoap/readAllRequest", output = "http://web/TransactionSoap/readAllResponse")
    public GroupOfServices readAll(
        @WebParam(name = "User", partName = "User")
        String user,
        @WebParam(name = "TransactionType", partName = "TransactionType")
        String transactionType);

    /**
     * 
     * @param endDate
     * @param user
     * @param startDate
     * @return
     *     returns web.GroupOfServices
     */
    @WebMethod
    @WebResult(name = "UserAndDate", partName = "UserAndDate")
    @Action(input = "http://web/TransactionSoap/readAllDateByUserRequest", output = "http://web/TransactionSoap/readAllDateByUserResponse")
    public GroupOfServices readAllDateByUser(
        @WebParam(name = "User", partName = "User")
        String user,
        @WebParam(name = "StartDate", partName = "StartDate")
        String startDate,
        @WebParam(name = "EndDate", partName = "EndDate")
        String endDate);

    /**
     * 
     * @param transaction
     */
    @WebMethod
    @Action(input = "http://web/TransactionSoap/createTransactionRequest", output = "http://web/TransactionSoap/createTransactionResponse")
    public void createTransaction(
        @WebParam(name = "Transaction", partName = "Transaction")
        Transaction transaction);

    /**
     * 
     * @param createAccount
     */
    @WebMethod
    @Action(input = "http://web/TransactionSoap/createAccountRequest", output = "http://web/TransactionSoap/createAccountResponse")
    public void createAccount(
        @WebParam(name = "CreateAccount", partName = "CreateAccount")
        Account createAccount);

    /**
     * 
     * @param password
     * @param user
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "GroupOfTransactions", partName = "GroupOfTransactions")
    @Action(input = "http://web/TransactionSoap/findByUsernameRequest", output = "http://web/TransactionSoap/findByUsernameResponse")
    public boolean findByUsername(
        @WebParam(name = "User", partName = "User")
        String user,
        @WebParam(name = "Password", partName = "Password")
        String password);

    /**
     * 
     * @param user
     * @return
     *     returns web.GroupOfServices
     */
    @WebMethod
    @WebResult(name = "TransactionUser", partName = "TransactionUser")
    @Action(input = "http://web/TransactionSoap/readUserRequest", output = "http://web/TransactionSoap/readUserResponse")
    public GroupOfServices readUser(
        @WebParam(name = "User", partName = "User")
        String user);

}
