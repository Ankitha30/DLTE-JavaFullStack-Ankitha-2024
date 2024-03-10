package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank {
     ArrayList<Loan> loans = new ArrayList<>();
     void addNewLoan(Loan loan) throws IOException,ClassNotFoundException;
     String checkAvailableLoans();
     void checkClosedLoans();

}
