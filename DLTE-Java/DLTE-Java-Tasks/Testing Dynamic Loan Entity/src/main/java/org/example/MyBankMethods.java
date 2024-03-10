package org.example;

import java.io.*;
import java.util.ArrayList;

public class MyBankMethods implements MyBank {
    public void addNewLoan(Loan loan) throws IOException,ClassNotFoundException {
        readLoansFromFile();
        loans.add(loan);
        writeLoanIntoFile();
    }
    public String checkAvailableLoans(){
        for(Loan loan:loans){
            if(loan.getLoanStatus().equalsIgnoreCase("open"))
                return loan.toString();
        }

        return null;
    }

    @Override
    public void checkClosedLoans() {
        for(Loan loan:loans){
            if(loan.getLoanStatus().equalsIgnoreCase("closed"))
                System.out.println(loan);
        }
    }
    private void readLoansFromFile() throws IOException,ClassNotFoundException{
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("loanD.txt"));
            loans.addAll((ArrayList<Loan>)objectInputStream.readObject());
            objectInputStream.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        }
    }
    private void writeLoanIntoFile(){
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("loanD.txt"));
//            SimpleFormatter simpleFormatter=new SimpleFormatter();
//            fileHandler.setFormatter(simpleFormatter);
            objectOutputStream.writeObject(loans);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
