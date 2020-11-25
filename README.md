# Employee Data Migration

---
This program reads a CSV file and stores the data of employees into a 
data transfer object. This is then migrated to a database.
The program is capable of multithreading.

## Requirements
- Persist data from a CSV to a database

## Functionality
- A CSV file can be entered by the user
- This file is read
- Any duplicate data is stored in a separate collection and is not persisted to the database
- A Data Transfer Object is used to persist the data to a database
- The data is split into chunks of 1000 records, each handed to a separate thread to persist to the database

## Epic
As a user I want to store my employee’s data in a database so that I can access it easily

## User Stories
1.	As a user I want to see if there is incorrect data in the file so that I can verify the data.
2.	As a user I want confirmation so that I know the data has transferred successfully.
3.	As a user I want to know how long it takes to migrate the data so that I can see if there is a problem
4.	As a user I want to know if there is duplicate data so that I can verify it
5.	As a user I want to store my employee’s data in a database so that I can access it easily

## Performance

### Concurrent Migration
By partitioning the data into chunks of 1000 records each and handing these chunks to threads,
the data migration is completed by 66 threads in 22 seconds.

By halving the partition size to 500, 131 threads migrated the data in 20 seconds.

### Sequential Migration
Without concurrency, the data migration is completed in 115 seconds.

--## User Guide
