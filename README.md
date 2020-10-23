# Employee Data Migration

---
This program reads a CSV file and stores the data of employees into a 
data transfer object. This is then migrated to a database.
The program is capable of multithreading.

##Performance

###Concurrent Migration
By partitioning the data into chunks of 1000 records each and handing these chunks to threads,
the data migration is completed by 66 threads in 22 seconds.

By halving the partition size to 500, 131 threads migrated the data in 20 seconds.

###Sequential Migration
Without concurrency, the data migration is completed in 115 seconds.