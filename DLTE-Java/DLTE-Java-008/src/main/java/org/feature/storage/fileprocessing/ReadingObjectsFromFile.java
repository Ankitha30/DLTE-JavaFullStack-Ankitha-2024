package org.feature.storage.fileprocessing;

import org.feature.storage.DebitCard;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadingObjectsFromFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file=new File("C:\\Users\\xxbhatka\\Documents\\Agenda.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

        DebitCard debitCard=(DebitCard) objectInputStream.readObject();

        System.out.println(debitCard.getDebitCardCvv()+" "+ debitCard.getDebitCardPin());

        objectInputStream.close();
        fileInputStream.close();
    }
}
