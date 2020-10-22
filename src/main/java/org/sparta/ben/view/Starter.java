package org.sparta.ben.view;

import org.sparta.ben.DAO;
import org.sparta.ben.IngestData;

public class Starter {
    public void start() {
        DAO dao = new DAO();
        IngestData ingestData = new IngestData();

        long startTime;
        long endTime;
        long elapsedTime;

        startTime = System.nanoTime();
        dao.createTable();
        dao.insertEmployee(ingestData.ingestData("resources/EmployeeRecords.csv"));
        endTime = System.nanoTime();
        elapsedTime = (endTime-startTime) / 1000000000;
        System.out.println("Time elapsed: " + elapsedTime + " seconds");

    }
}
