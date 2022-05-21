### Car Resource REST-API

A simple spring-boot app with an H2 database, for submitting a ticket for the company, in case you need maintenance service for your car.

So far, regarding the task description, the only available API is a CRUD for TicketService.

Use bellow command to run the app

```mvn spring-boot:run```

After running the command server will run on 8080 port. There is already a customer with two vehicles in the DB. You can use bellow curl submit, get, update, and close tickets.

- Submit a ticket

```
curl --location --request POST 'http://localhost:8080/customer-tickets' \
   --header 'Content-Type: application/json' \
   --data-raw '{
       "customerId":1,
       "description":"FOR BENZ",
       "vehicleId":1
   }'\
```

- Get tickets for the customer

```
curl --location --request GET 'http://localhost:8080/customer-tickets/1' \
--data-raw ''
```

- Update the ticket

```
curl --location --request PUT 'http://localhost:8080/customer-tickets' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerId":1,
    "description":"new T",
    "ticketId":1
}'
```

- Close the ticket

```
curl --location --request PUT 'http://localhost:8080/customer-tickets' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerId":1,
    "description":"new T",
    "ticketId":1
}'
```