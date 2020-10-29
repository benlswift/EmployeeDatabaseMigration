package org.sparta.ben.start;

import org.sparta.ben.start.Starter;

/**
 * For this project, you will be performing a data migration exercise from a CSV file to a SQL database.
 * The file contains details about Employees
 *
 *
 * Your program will need to read the data from the CSV file and then import the data into the database.
 * Please note that there may be errors in the CSV file that you need to check for.
 * It is recommend to create some kind of collection to store the invalid employee records so they can be checked later
 *
 * The program needs thorough testing at all points to ensure the data migration has taken place correctly.
 * It should also report on the time takes to read the CSV, populate the database and the overall time for the migration. Raise any exceptions required it requires to.
 *
 *
 * Be sure to consider the use of design patterns to follow good clean code standards (DTO, DAO)
 *
 * Submission:
 * - Code should be hosted on your own Github page with a suitable README.md file
 * - Pull request by 23rd Oct Friday EOD
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        Starter starter = new Starter();
        starter.start();
    }
}
