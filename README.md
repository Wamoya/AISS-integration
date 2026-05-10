# AISS - Integration project

Deliverable for the AISS subject. GitHub repository: https://github.com/Wamoya/AISS-integration

## Project specifications

- Video Miner *(default port: 8080)*
- Peertube Miner *(default port: 8081)*
- Dailymotion Miner *(default port: 8082)*

**ID generation strategy:** In the database, we have opted for the ID generation strategy of assigning integer values to each object, in incremental order.
  - Maintaining the original IDs of their respective sources gives the advantage of an easier traceability towards the original resource, at the expense of making the system more complex, having to deal with the possibility of, for example, two videos from different sources sharing the same original ID.
  - An **extension point**, if we finally decide that traceability is important for our project, would be to store the original ID of each object in a new column in the database.

We have implemented **pagination and throttling** logic to avoid error 429 when interacting with the more restrictive Peertube API.
  - Both miners also uses pagination logic to get all the requested resources together before sending the response back to the developer / Video Miner app.

We have **tweaked the provided Postman test suite** for it to be compatible with our ID generation strategy, **as well as expanded it** with more tests, including but not limited to tests for the pagination and throttling logic of the miners.

Also, each one of the miners comes with its own set of tests to check the correct functioning of the different methods that have been defined.

#### Video Miner

![Main data model](./assets/data_model-videominer.png "Main data model")
