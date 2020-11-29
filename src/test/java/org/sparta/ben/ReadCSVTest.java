package org.sparta.ben;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.sparta.ben.controller.EmployeeDTO;
import org.sparta.ben.inputreader.CSVFileReader;

import java.util.ArrayList;
import java.util.List;

public class ReadCSVTest
{

    @Test
    public void readCSVTest()
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        CSVFileReader CSVFileReader = new CSVFileReader();
        List<EmployeeDTO> employees = new ArrayList<>();
        employees = CSVFileReader.readCSVFile("resources/EmployeeRecords.csv");
        Assertions.assertEquals(9943, employees.size());
    }
}
