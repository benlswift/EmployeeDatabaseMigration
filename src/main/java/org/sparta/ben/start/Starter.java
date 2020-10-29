package org.sparta.ben.start;

import org.sparta.ben.model.EmployeeDAO;
import org.sparta.ben.fileReader.ReadCSVFile;
import org.sparta.ben.view.Printer;

public class Starter {
    EmployeeDAO dao = new EmployeeDAO();
    ReadCSVFile readCSVFile = new ReadCSVFile();
    public void start() {

        long startTime;
        long endTime;
        long elapsedTime;
        Printer.print("---Sequential Migration---");
        startTime = System.nanoTime();
        startSequential();
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;
        Printer.print("Time elapsed: " + elapsedTime + " seconds");
        Printer.print("\n---Concurrent Migration---");
        startTime = System.nanoTime();
        startConcurrent();
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;//convert to seconds
        Printer.print("Time for data migration: " + elapsedTime + " seconds");


    }
    public void startSequential(){
        dao.createTable();
        dao.insertEmployee(readCSVFile.readCSVFile("resources/EmployeeRecords.csv"));
    }
    public void startConcurrent(){
        dao.createTable();
        dao.insertEmployeeConcurrent(readCSVFile.readCSVFile("resources/EmployeeRecords.csv"));
    }
}
