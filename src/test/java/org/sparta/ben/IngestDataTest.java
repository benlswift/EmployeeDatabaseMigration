package org.sparta.ben;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.sparta.ben.controller.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class IngestDataTest
{

    @Test
    public void ingestDataTest()
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        IngestData ingestData = new IngestData();
        List<EmployeeDTO> employees = new ArrayList<>();
        employees = ingestData.ingestData("resources/EmployeeRecords.csv");
        Assertions.assertEquals(198429, employees.get(0).getEmpID());
    }
}
