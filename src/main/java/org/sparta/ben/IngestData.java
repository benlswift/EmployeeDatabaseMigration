package org.sparta.ben;

import org.sparta.ben.controller.EmployeeDTO;

import java.io.*;
import java.util.*;

public class IngestData {
    public List<EmployeeDTO> ingestData(String filePath){

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
                if (!listOfKeys.contains(line.split(",")[0])){
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setEmpID(Integer.parseInt(line.split(",")[0]));
                    employeeDTO.setPrefix(line.split(",")[1]);
                    employeeDTO.setFirst_name(line.split(",")[2]);
                    employeeDTO.setMiddle_initial(line.split(",")[3]);
                    employeeDTO.setLast_name(line.split(",")[4]);
                    employeeDTO.setGender(line.split(",")[5]);
                    employeeDTO.setEmail(line.split(",")[6]);
                    employeeDTO.setDob(line.split(",")[7]);
                    employeeDTO.setDateOfJoining(line.split(",")[8]);
                    employeeDTO.setSalary(line.split(",")[9]);
                    employees.add(employeeDTO);
                    listOfKeys.add(line.split(",")[0]);
                    //check if email is valid
                    //still persist record but also add to another list
                    if(!dataVerification.verifyEmail(line.split(",")[6])){
                        listOfInvalidData.add(line);
                    }

                }
                else{
                    listOfDuplicates.add(line);
                }
            }
            System.out.println("No of duplicates: " + listOfDuplicates.size());

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
