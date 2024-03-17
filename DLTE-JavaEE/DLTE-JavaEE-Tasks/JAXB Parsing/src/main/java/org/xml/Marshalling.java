package org.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Marshalling {
    public static void main(String[] args) throws FileNotFoundException, JAXBException {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(new Date("09/08/2023"), 9000.0, "anu"));
        transactionList.add(new Transaction(new Date("09/23/2023"), 70000.0, "anu"));
        transactionList.add(new Transaction(new Date("06/08/2023"), 6000.0, "anu"));
        transactionList.add(new Transaction(new Date("09/07/2023"), 7000.0, "raju"));
        transactionList.add(new Transaction(new Date("09/05/2023"), 9300.0, "latha"));
        Wrapper transactionWrapper=new Wrapper();
        transactionWrapper.setTransactionList(transactionList);
        JAXBContext context = JAXBContext.newInstance(Wrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(transactionWrapper, new FileOutputStream("scheme.xml"));
    }

}























