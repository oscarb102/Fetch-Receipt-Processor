# Fetch Receipt Processor

The following webservice is intended to serve as an API to process receipts. Without the use of a database, this service will store information in memory. The following functions are:
- Process Receipts
- Get Points (for receipt)

## How To Run Application (using Dockerfile)
In order to run the application, run the following docker commands:

```
docker build -t receipt-image
```

Once the image is created, validate that the docker image has been created by running:

```
docker images
```

After validating, you can now run the application using the following command:

```
docker run -p 8080:8080 receipt-image
```

## API Endpoints

With the application now active and running on port 8080 of the localhost, API endpoints can now be reached. For more information, refer to the [GitHub Project Description](https://github.com/fetch-rewards/receipt-processor-challenge).

### Endpoint: Process Receipts

In order to process a receipt, use the following endpoint with Payload following the structure below:

```
POST: localhost:8080/receipts/process
```

#### Payload:
```json
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}
```

#### Example Response:
```json
{ "id": "dEP3QBKA-6efB-y9Me-Tfh6-hmReWtR399sAqK66" }
```

### Endpoint: Get Points

Use the following endpoint to calculate the points attached to the supplied Receipt ID (if it exists):

```
GET: localhost:8080/receipts/{id}/points
```

#### Example Response:
```json
{ "points": "109" }
```