package org.sparta.ben.fileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sparta.ben.start.DataVerification;
import org.sparta.ben.controller.EmployeeDTO;
import org.sparta.ben.view.Printer;

import java.io.*;
import java.util.*;

public class ReadCSVFile {
    public List<EmployeeDTO> readCSVFile(String filePath){
        long startTime;
        long endTime;
        long elapsedTime;
        startTime = System.nanoTime();
        File file = new File(filePath);
        DataVerification dataVerification = new DataVerification();
        List<String> listOfDuplicates = new ArrayList();
        List<String> listOfInvalidData = new ArrayList<>();
        Set<String> set = new HashSet<>();
        String line;
        List<EmployeeDTO> employees = new ArrayList();
        List<String> listOfKeys = new ArrayList();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            line = bufferedReader.readLine();//column names
            while ((line = bufferedReader.readLine()) != null){
                String[] splitLine = line.split(",");
                if (!listOfKeys.contains(splitLine[0])){
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setEmpID(Integer.parseInt(line.split(",")[0]));
                    employeeDTO.setPrefix(splitLine[1]);
                    employeeDTO.setFirst_name(splitLine[2]);
                    employeeDTO.setMiddle_initial(splitLine[3]);
                    employeeDTO.setLast_name(splitLine[4]);
                    employeeDTO.setGender(splitLine[5]);
                    employeeDTO.setEmail(splitLine[6]);
                    employeeDTO.setDob(splitLine[7]);
                    employeeDTO.setDateOfJoining(splitLine[8]);
                    employeeDTO.setSalary(splitLine[9]);
                    employees.add(employeeDTO);
                    listOfKeys.add(splitLine[0]);
                    //check if email is valid
                    //still persist record but also add to another list
                    if(!dataVerification.verifyEmail(splitLine[6])){
                        listOfInvalidData.add(line);
                    }

                }
                else{
                    listOfDuplicates.add(line);
                }
            }
            endTime = System.nanoTime();

            Printer.print("Number of duplicates: " + listOfDuplicates.size());
            if(listOfInvalidData.size() !=0){
                Printer.print("Number of invalid records: " + listOfInvalidData.size());
            }
            elapsedTime = (endTime-startTime) / 1000000000;//convert to seconds
            Printer.print("Time to read file: " + elapsedTime + " seconds");

            } catch (FileNotFoundException e) {
            Logger logger = LogManager.getLogger();
            logger.error("File not found " + e.getMessage());
            Printer.printError("Please enter a valid file name");
            System.exit(0);

        } catch (IOException e) {
            Logger logger = LogManager.getLogger();
            logger.error(e.getMessage());
        }
        return employees;
    }
}
