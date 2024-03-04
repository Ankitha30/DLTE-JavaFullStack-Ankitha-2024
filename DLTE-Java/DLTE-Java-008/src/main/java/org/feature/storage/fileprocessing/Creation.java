package org.feature.storage.fileprocessing;

import java.io.File;
import java.io.IOException;

public class Creation {
    public static void main(String[] args) {
        File file=new File("C:\\DLTE-JavaFullStack-Ankitha-2024\\DLTE-Java\\DLTE-Java-008");
//        File file=new File("debits.doc");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
