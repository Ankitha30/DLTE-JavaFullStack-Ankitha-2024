package org.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyTransactionFunction extends Remote {
    List<String> fetchOver() throws RemoteException;
    List<String> fetchByType(String name,String type);

}
