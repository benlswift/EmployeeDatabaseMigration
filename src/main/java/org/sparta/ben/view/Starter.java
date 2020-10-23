package org.sparta.ben.view;

import org.sparta.ben.ConcurrentDataPersistance;
import org.sparta.ben.DAO;
import org.sparta.ben.IngestData;

public class Starter {
    public void start() {
        DAO dao = new DAO();
        IngestData ingestData = new IngestData();

        long startTime;
        long endTime;
        long elapsedTime;
//        System.out.println("Sequential Migration");
//        startTime = System.nanoTime();
//        dao.createTable();
//        dao.insertEmployee(ingestData.ingestData("resources/EmployeeRecordsLarge.csv"));
//        endTime = System.nanoTime();
//        elapsedTime = (endTime-startTime) / 1000000000;
//        System.out.println("Time elapsed: " + elapsedTime + " seconds");

        System.out.println("Concurrent Migration");
        startTime = System.nanoTime();
        dao.createTable();
        dao.insertEmployeeConcurrent(ingestData.ingestData("resources/EmployeeRecordsLarge.csv"));
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;
        System.out.println("Time elapsed: " + elapsedTime + " seconds");

    }
}
