package web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;


public class SoapEndpoints {
    private static String url="http://localhost:2002/ankitha";
    private static Logger logger= LoggerFactory.getLogger(SoapEndpoints.class);
    public static void main(String[] args) {
        TransactionSoap transactionSoap = new TransactionSoap();
        logger.info("Web service Hosted");
        System.out.println("Web service hosted at "+ url);
        Endpoint.publish(url,transactionSoap);
    }
}
