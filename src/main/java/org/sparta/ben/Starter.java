package org.sparta.ben;

public class Starter {
    public void start() {
        DAO dao = new DAO();
        IngestData ingestData = new IngestData();
        dao.createTable();
        dao.insertEmployee(ingestData.ingestData());
    }

}
