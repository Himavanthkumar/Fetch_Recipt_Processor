## Receipt Processor
A Java-based web service built with Spring Boot to process receipts and calculate points based on predefined rules, as specified in the provided api.yml. The service implements two REST endpoints:

1. POST /receipts/process: Accepts a receipt JSON and returns a unique ID.

2. GET /receipts/{id}/points: Returns the points awarded for a receipt by ID.

Data is stored in memory (no persistence) and the application is Dockerized for easy setup.

## Prerequisites
1. Docker
2. Maven(Optional if you are running it in docker)
3. Java(Optional if you are running it in docker)

## Steps
1. git clone https://github.com/Himavanthkumar/Fetch_Recipt_Processor.git
2. cd receipt-processor
3. mvn clean package
This step generates a package in /target folder check for target/receipt_processor-0.0.1-SNAPSHOT.jar or something similar
4. docker build -t receipt-processor .
Before step 4 make sure you run step 3
5.docker run -p 8080:8080 receipt-processor

The service will be available at http://localhost:8080 .
