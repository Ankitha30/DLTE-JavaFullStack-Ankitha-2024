package org.repository;

import org.middleware.Activity;
import org.middleware.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyzer implements Activity {
     private static Logger logger= LoggerFactory.getLogger(DataAnalyzer.class);
     private static String filePath ="C:\\Users\\xxbhatka\\Documents\\JavaDatabase\\employee details.txt";
   //  System.setProperty("logbackConfiguration","C:\\Users\\xxbhatka\\Documents\\Review\\logback.xml");


    public void addEmployees(List<EmployeeDetails> list) {
        if(list == null || list.isEmpty()) {
            logger.warn("No employees to add");
            return;
        }
      try {
          writeToFile(list);
      }catch (IOException  e){

      }

    }

    private void writeToFile(List<EmployeeDetails> list) throws IOException{

            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
            if (file.exists() && file.length() >0){
                logger.info("Data has been successfully written to the file.");
                System.out.println("Data has been successfully written to the file.");
            }
            else
            {
                logger.error("Error occurred while writing to file");
                System.out.println("Data Not written");
            }



    }

    @Override
    public EmployeeDetails getEmployeeById(int id) {

        try{
            List<EmployeeDetails> employees = readFromFile();
        for(EmployeeDetails employeeDetails:employees){
            if(employeeDetails.getPersonalDetails().getEmployeeId()==id){
                return employeeDetails;
            }
        }
        }catch (IOException | ClassNotFoundException e){
            logger.error("Error occurred while reading from the file "+e.getMessage());
        }

        return null;
    }

    private List<EmployeeDetails> readFromFile()  throws IOException,ClassNotFoundException{
        ArrayList<EmployeeDetails> employeeDetails = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employeeDetails = (ArrayList<EmployeeDetails>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        return employeeDetails;
    }

    @Override
    public List<EmployeeDetails> employeeByPinCode(String pin)  {
        List<EmployeeDetails> matchList = new ArrayList<>();
        try {
            List<EmployeeDetails> employees = readFromFile();

            for (EmployeeDetails employeeDetails : employees) {
                if (employeeDetails.getAddressDetails().getPermanentAddressPin().equals(pin)) {
                    matchList.add(employeeDetails);
                }
            }
        }catch (IOException | ClassNotFoundException e){
            logger.error("Error occurred while reading the file: "+e.getMessage());
        }
        return matchList;
    }

    @Override
    public List<EmployeeDetails> getEmployees() {
        try {
            File file = new File(filePath);
            if(file.exists())
                return readFromFile();
            else
            {
              logger.info("File does not exists");
            }
        }catch (IOException | ClassNotFoundException error){
            logger.error("Error in reading the file"+error.getMessage());
            System.out.println("No data found");
        }
        return null;
    }
}
