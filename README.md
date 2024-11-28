# Fetch Receipt Processor

The following webservice is intended to serve as an API to process receipts. Wihtout the use of a database, this service will store information in memory. The following functions are:
- Process Receipts
- Get Points (for receipt)

## How To Run (with Dockerfile)
In order to run the application, run the following docker commands:

```docker build -t receipt-image .```

Once the image is created, you can run the application using the following command:

```docker run -p 8080:8080 receipt-image```

Then, insertions can be added using the provided instructions within the [GitHub Project Description](https://github.com/fetch-rewards/receipt-processor-challenge).