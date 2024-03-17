package org.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class TransactionUnmarshalling {
    public static void main(String[] args) throws JAXBException {
        File file=new File("scheme.xml");
        Scanner scanner=new Scanner(System.in);
        JAXBContext context= JAXBContext.newInstance(Wrapper.class);
        Unmarshaller unmarshaller= context.createUnmarshaller();
        Wrapper transactions = (Wrapper) unmarshaller.unmarshal(file);
        System.out.println("Enter the username");
        String name=scanner.next();
        for (Transaction transaction:transactions.getTransactionList()){
            if(transaction.getTransactionTo().equals(name)){
                System.out.println("Name: "+transaction.getTransactionTo()+" "+"       Amount: "+transaction.getAmount()+"      Date: "+transaction.getDateOfTransaction());

            }
        }

    }
}
