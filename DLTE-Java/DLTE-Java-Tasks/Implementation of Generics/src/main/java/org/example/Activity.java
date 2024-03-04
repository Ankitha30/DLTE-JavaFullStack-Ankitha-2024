package org.example;
/*
Generic Interface "Activity" : Generic abstract CRUD methods

Generic Class "MyBankDatabase": Generic Array of Objects

implement Activity to MyBankDatabase

MAIN :

Create instance to Generic MyBankDatabse to store CreditCard object and perform CRUD
Create instance to Generic MyBankDatabase to store Transaction object and perform CRUD
 */

import java.util.Arrays;

public interface Activity<T> {
    T read(int index);
    String delete(int index);
    void update(int index,T object);


    void viewAll();
}
