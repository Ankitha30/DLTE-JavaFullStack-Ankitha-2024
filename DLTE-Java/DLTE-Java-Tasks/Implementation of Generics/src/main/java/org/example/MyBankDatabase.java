package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class MyBankDatabase<T> implements Activity<T> {
   T[] typeObject;
    public String insertNewRecord(T objects) {
        int size=typeObject.length;
        for(int index=0;index< size;index++){
            if(typeObject[index]==null){
                typeObject[index]=objects;
                return objects+" has inserted";
            }
        }
        return objects+" has inserted";
    }

    @Override
    public T read(int index) {
        if(index >= 0 && index < typeObject.length)
            return  typeObject[index];
        return null;
    }

    @Override
//    public String delete(int index) {
//        if(index>=0 && index< typeObject.length && typeObject[index]!=null){
//            System.out.println(typeObject[index]);
//            T object = typeObject[index];
//            typeObject[index]=null;
//            return object+ " has deleted.";
//        }
//        return null;
//    }
    public String delete(int index) {
        if(index>=0&&index< typeObject.length&&typeObject[index]!=null){
            T object=typeObject[index];
            typeObject[index]=null;
            return object+" has deleted";
        }
        return null;
    }


    public void update(int index, T object) {
        if(index >= 0 && index <typeObject.length){
            typeObject[index]=object;
            System.out.println(object+ " has updated at "+ index);
        }
        else
            System.out.println(object+" hasn't updated at "+ index);

    }

    @Override
    public void viewAll() {
        System.out.println(Arrays.toString(typeObject));
    }
}
