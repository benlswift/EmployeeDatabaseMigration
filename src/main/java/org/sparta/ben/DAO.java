package org.sparta.ben;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.sparta.ben.controller.EmployeeDTO;
import java.util.*;

public class DAO {
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

    public void useMyLocalDB(){
        String useLocalDB = "USE myLocal";
        try {
            PreparedStatement preparedStatement = connectionToDB().prepareStatement(useLocalDB);
            int hasRun = preparedStatement.executeUpdate();
            //precompiled statement
            //can execute many times
            if (hasRun == 1)
            {
                System.out.println(("Success"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(){
        String dropTable = "DROP TABLE employees";
        String createTable = "CREATE TABLE employees (EmpID INT AUTO_INCREMENT PRIMARY KEY," +
                "Name_Prefix VARCHAR(20),First_Name VARCHAR(50),Middle_Initial VARCHAR(50), Last_Name VARCHAR(50)," +
                "Gender VARCHAR(50),EMail VARCHAR(50),Date_of_Birth VARCHAR(50),Date_of_Joining VARCHAR(50),Salary VARCHAR(50))";
        try {
            PreparedStatement preparedStatement = connectionToDB().prepareStatement(dropTable);
            preparedStatement.executeUpdate();
            preparedStatement = connectionToDB().prepareStatement(createTable);
            int hasRun = preparedStatement.executeUpdate();
            //precompiled statement
            //can execute many times
            if (hasRun == 1)
            {
                System.out.println(("Success"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEmployee(List<EmployeeDTO> employeeDTO){
        String insert = "INSERT INTO employees VALUES ";
        PreparedStatement preparedStatement = null;
        try {
            for (EmployeeDTO e : employeeDTO){
            insert+= "("+ e.getEmpID() + ",'" + e.getPrefix() + "','" + e.getFirst_name() + "','"
                + e.getMiddle_initial() + "','" + e.getLast_name() + "','" + e.getGender() + "','" + e.getEmail()
            + "','" + e.getDob() + "','" + e.getDateOfJoining() + "','" + e.getSalary() +"')," ;
        }

            insert= insert.substring(0, insert.length() - 1 );//remove final comma
            preparedStatement = connectionToDB().prepareStatement(insert);
            int hasRun = preparedStatement.executeUpdate();
            if (hasRun == 1)
            {
                System.out.println(("Success"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}