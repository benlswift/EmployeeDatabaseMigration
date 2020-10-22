package org.sparta.ben;

import org.sparta.ben.controller.EmployeeDTO;

import java.io.*;
import java.util.*;

public class IngestData {
    public List<EmployeeDTO> ingestData(){

        File file = new File("resources/EmployeeRecords.csv");
//        List<String> listOfData = new ArrayList();
//        List<String> listOfColumns = new ArrayList();
        List<String> listOfDuplicates = new ArrayList();
        Set<String> set = new HashSet<>();
        String line;
        List<EmployeeDTO> employees = new ArrayList();
        List<String> listOfKeys = new ArrayList();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(file))) {
            line = bufferedReader.readLine();
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
                    employees.add(employeeDTO);

                    listOfKeys.add(line.split(",")[0]);
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
