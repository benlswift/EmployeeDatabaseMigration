# Employee Data Migration

---
##Performance

###Concurrent Migration
By partitioning the data into chunks of 1000 records each and handing these chunks to threads,
the data migration is completed by 66 threads in 22 seconds.

###Sequential Migration
Without concurrency, the data migration is completed in 115 seconds.