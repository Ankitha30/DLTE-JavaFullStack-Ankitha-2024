package org.example;



import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.InflaterInputStream;


public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> creditList = new ArrayList<>();

    @Override
    public void create(T object) throws IOException {
        creditList.add(object);
        writeToFile();

    }

    private void writeToFile() throws IOException {
        File file = new File("Credit.txt");

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditList);

        objectOutputStream.close();
        fileOutputStream.close();


//        File file1 = new File("TestData.txt");
//        FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
//        ObjectOutputStream objectOutputStream2 =new ObjectOutputStream(fileOutputStream1);
//        objectOutputStream2.writeBytes(String.valueOf(creditList));
//        objectOutputStream2.close();
//        fileOutputStream1.close();

    }

    private void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Credit.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        creditList = (ArrayList<T>) objectInputStream.readObject();
        System.out.println(creditList);
        objectInputStream.close();
        fileInputStream.close();



    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> myBankDatabase = new MyBankDatabase<>();

        CreditCard creditCard1= new CreditCard(23432234L, "Ankitha", new Date("4/20/2023"), 211, 50000);
        CreditCard creditCard2= new CreditCard(23745134L, "Anu", new Date("4/10/2023"), 452, 70000);
        CreditCard creditCard3= new CreditCard(23432234L, "Anusha", new Date("4/11/2023"), 342, 60000);
        myBankDatabase.create(creditCard1);
        myBankDatabase.create(creditCard2);
        myBankDatabase.create(creditCard3);
        myBankDatabase.writeToFile();
        System.out.println("Successfully inserted");
        System.out.println("File contains following data");
        myBankDatabase.readFromFile();
    }
}
