package org.sparta.ben.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sparta.ben.controller.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConcurrentEmployeeDAO implements Runnable
{
    List<EmployeeDTO> employeeDTO;
    Connection connection;
    public ConcurrentEmployeeDAO(List<EmployeeDTO> employeeDTO, Connection connection){
        this.employeeDTO = employeeDTO;
        this.connection = connection;
    }
    @Override
    public void run() {
        synchronized (this){
            String insert = "INSERT INTO employees VALUES ";
            PreparedStatement preparedStatement = null;
            try {
                for (EmployeeDTO e : employeeDTO){
                    insert+= "("+ e.getEmpID() + ",'" + e.getPrefix() + "','" + e.getFirst_name() + "','"
                            + e.getMiddle_initial() + "','" + e.getLast_name() + "','" + e.getGender() + "','" + e.getEmail()
                            + "','" + e.getDob() + "','" + e.getDateOfJoining() + "','" + e.getSalary() +"')," ;
                }
                insert= insert.substring(0, insert.length() - 1 );//remove final comma
                preparedStatement = connection.prepareStatement(insert);
                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Logger logger = LogManager.getLogger();
                logger.error("Error in SQL insertion: " + throwables.getMessage());


            }

        }
    }


}
