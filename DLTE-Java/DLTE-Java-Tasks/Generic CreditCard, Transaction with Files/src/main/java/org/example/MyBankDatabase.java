package org.example;



import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> creditList=new ArrayList<>();

    @Override
    public void create(T object) throws IOException {
        creditList.add(object);
        writeIntoFile();

    }

    private void writeIntoFile() throws IOException {
    File file = new File("Credit.txt");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(creditList);
    objectOutputStream.close();
    fileOutputStream.close();
    }
//    private void readFromFile() throws IOException, ClassNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream("Credit.txt");
//        ObjectInputStream objectInputStream =new ObjectInputStream(fileInputStream);
//        creditList = (ArrayList<T>) objectInputStream.readObject();
//        System.out.println(creditList);
//        objectInputStream.close();
//        fileInputStream.close();

        /*
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] current=new byte[fileInputStream.available()];
        fileInputStream.read(current);
        String collectedData=new String(current);
        System.out.println("File contains following content\n"+collectedData);
        fileInputStream.close();
         */
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> myBankDatabase = new MyBankDatabase<>();

        CreditCard creditCard1= new CreditCard(23432234L, "Ankitha", new Date("4/20/2023"), 211, 50000);
////        CreditCard creditCard2= new CreditCard(23745134L, "Anu", new Date("4/10/2023"), 452, 70000);
////        CreditCard creditCard3= new CreditCard(23432234L, "Ankitha", new Date("4/20/2023"), 211, 50000);
        myBankDatabase.create(creditCard1);
//        myBankDatabase.create(creditCard2);
//        myBankDatabase.create(creditCard3);
        myBankDatabase.writeIntoFile();
//        myBankDatabase.readFromFile();
 }

}
