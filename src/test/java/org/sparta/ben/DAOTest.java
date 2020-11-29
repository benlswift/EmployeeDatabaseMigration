package org.sparta.ben;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.sparta.ben.controller.EmployeeDTO;
import org.sparta.ben.inputreader.CSVFileReader;
import org.sparta.ben.model.EmployeeDAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DAOTest {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    CSVFileReader CSVFileReader = new CSVFileReader();
    EmployeeDAO dao = new EmployeeDAO();
    List<EmployeeDTO> employees = new ArrayList<>();

    private String URL = "jdbc:mysql://localhost:3306/myLocal?serverTimezone=GMT";
    private Properties properties = new Properties();
    private Connection connection = null;
    private Connection connectionToDB(){
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty(
                    "username"), properties.getProperty("password")
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
    @Test
    public void numberOfRecordsTest(){
        int dbValue;
        dao.createTable();
        dao.insertEmployee(CSVFileReader.readCSVFile("resources/EmployeeRecords.csv"));

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectionToDB().prepareStatement("SELECT COUNT(*) FROM myLocal.employees");
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            dbValue = result.getInt("count(*)");
            Assertions.assertEquals(9943,dbValue);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
