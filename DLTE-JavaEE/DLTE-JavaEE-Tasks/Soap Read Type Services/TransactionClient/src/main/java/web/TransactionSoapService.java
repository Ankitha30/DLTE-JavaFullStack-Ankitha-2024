
package web;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TransactionSoapService", targetNamespace = "http://web/", wsdlLocation = "http://localhost:1020/ankitha?wsdl")
public class TransactionSoapService
    extends Service
{

    private final static URL TRANSACTIONSOAPSERVICE_WSDL_LOCATION;
    private final static WebServiceException TRANSACTIONSOAPSERVICE_EXCEPTION;
    private final static QName TRANSACTIONSOAPSERVICE_QNAME = new QName("http://web/", "TransactionSoapService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1020/ankitha?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRANSACTIONSOAPSERVICE_WSDL_LOCATION = url;
        TRANSACTIONSOAPSERVICE_EXCEPTION = e;
    }

    public TransactionSoapService() {
        super(__getWsdlLocation(), TRANSACTIONSOAPSERVICE_QNAME);
    }

    public TransactionSoapService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRANSACTIONSOAPSERVICE_QNAME, features);
    }

    public TransactionSoapService(URL wsdlLocation) {
        super(wsdlLocation, TRANSACTIONSOAPSERVICE_QNAME);
    }

    public TransactionSoapService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRANSACTIONSOAPSERVICE_QNAME, features);
    }

    public TransactionSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TransactionSoapService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TransactionSoap
     */
    @WebEndpoint(name = "TransactionSoapPort")
    public TransactionSoap getTransactionSoapPort() {
        return super.getPort(new QName("http://web/", "TransactionSoapPort"), TransactionSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TransactionSoap
     */
    @WebEndpoint(name = "TransactionSoapPort")
    public TransactionSoap getTransactionSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://web/", "TransactionSoapPort"), TransactionSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRANSACTIONSOAPSERVICE_EXCEPTION!= null) {
            throw TRANSACTIONSOAPSERVICE_EXCEPTION;
        }
        return TRANSACTIONSOAPSERVICE_WSDL_LOCATION;
    }

}
