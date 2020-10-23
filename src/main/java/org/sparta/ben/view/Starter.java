package org.sparta.ben.view;

import org.sparta.ben.data.EmployeeDAO;
import org.sparta.ben.ReadCSVFile;

public class Starter {
    public void start() {
        EmployeeDAO dao = new EmployeeDAO();
        ReadCSVFile readCSVFile = new ReadCSVFile();

        long startTime;
        long endTime;
        long elapsedTime;
//        System.out.println("Sequential Migration");
//        startTime = System.nanoTime();
//        dao.createTable();
//        dao.insertEmployee(ingestData.ingestData("resources/EmployeeRecords.csv"));
//        endTime = System.nanoTime();
//        elapsedTime = (endTime-startTime) / 1000000000;
//        System.out.println("Time elapsed: " + elapsedTime + " seconds");

        Printer.print("Concurrent Migration");
        startTime = System.nanoTime();
        dao.createTable();
        dao.insertEmployeeConcurrent(readCSVFile.readCSVFile("resources/EmployeeRecordsLarge.csv"));
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;//convert to seconds
        Printer.print("Time for data migration: " + elapsedTime + " seconds");

    }
}
