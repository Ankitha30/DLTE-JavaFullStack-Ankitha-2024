package org.feature.storage.fileprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadingFromFile {
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\xxbhatka\\Pictures\\Agenda - Copy.txt");
//        File file=new File("C:\\Users\\xxbhatka\\Documents\\Agenda.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] current=new byte[fileInputStream.available()];
        fileInputStream.read(current);
        String collectedData=new String(current);
        System.out.println("File contains following content\n"+collectedData);
        fileInputStream.close();
    }
}
