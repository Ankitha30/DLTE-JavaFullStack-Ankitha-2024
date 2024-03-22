package org.rmi;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.services.TransactionService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyTransactionServer extends UnicastRemoteObject implements MyTransactionFunction, Serializable {
    private static Context context;
    private Registry registry;
    private TransactionService services;
    @Override
    public List<String> fetchOver() throws RemoteException {
        List<Transaction> transactions=services.callViewAllTransaction().stream().filter(each->each.getTransactionAmount()>=1300).collect(Collectors.toList());
        List<String> returned = new ArrayList<>();
        for(Transaction transfer:transactions)
            returned.add(transfer.getUserName()+" "+transfer.getTransactionAmount());
        return returned;
        }

    @Override
    public List<String> fetchByType(String name, String type) {
        List<Transaction> transactions = services.callFindByType(name,type).stream().filter(each->each.getTransactionType("deposit").equals(type)).collect(Collectors.toList());
        List<String> returned = new ArrayList<>();
        for(Transaction transfer:transactions)
            returned.add(transfer.getUserName()+" "+transfer.getTransactionAmount());
        return returned;
    }

    public MyTransactionServer() throws  RemoteException {
            super();
            services=new TransactionService(new DatabaseTarget());
            try {
                registry = LocateRegistry.createRegistry(3030);
                Hashtable properties = new Hashtable();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
                properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
                context = new InitialContext(properties);

            } catch (NamingException e) {
                throw new RuntimeException(e);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }


        }

    public static void main(String[] args) throws RemoteException, NamingException {
        MyTransactionServer myTransactionServer = new MyTransactionServer();
        context.bind("java:/transaction-filter",myTransactionServer);
    }

}
