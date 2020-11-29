package org.sparta.ben.start;

import org.sparta.ben.inputreader.UserInputReader;
import org.sparta.ben.model.EmployeeDAO;
import org.sparta.ben.inputreader.CSVFileReader;
import org.sparta.ben.view.Printer;

public class Starter {
    EmployeeDAO dao = new EmployeeDAO();
    CSVFileReader CSVFileReader = new CSVFileReader();
    public void start() {

        long startTime;
        long endTime;
        long elapsedTime;
        String fileName;
        int migrationType;

        fileName = UserInputReader.readUserInputString("Please enter file name");
        migrationType = UserInputReader.readUserInputInt("Please choose: \n1. Sequential \n2. Concurrent");
        startTime = System.nanoTime();
        if (migrationType == 1){
            startSequential(fileName);
        }
        else if (migrationType == 2){
            startConcurrent(fileName);
        }
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;//convert to seconds
        Printer.print("Time for data migration: " + elapsedTime + " seconds");
        Printer.print("Data successfully migrated");
    }
    public void startSequential(String fileName){
        dao.createTable();
        dao.insertEmployee(CSVFileReader.readCSVFile("resources/"+fileName));
    }
    public void startConcurrent(String fileName){
        Printer.print("\n---Concurrent Migration---");
        dao.createTable();
        dao.insertEmployeeConcurrent(CSVFileReader.readCSVFile("resources/"+fileName));
    }
}
