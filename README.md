# EmployeeManagmentSimpleService
Task S4.02 Simple HTTP Service
Description


In this exercise you will delve into HTTP requests (GET, POST, PUT DELETE). You will also use layer structure and MVC pattern thus applying an architectural pattern to the project.

Level 1
In this practice you will learn to create HTTP requests with a complete CRUD (Create, Read, Update, Delete). That is why we will implement an MVC architecture pattern with repositories.

Statement:

We will create a very simple employee management program where depending on the employee's job you will be automatically assigned a salary. We identify the name and job of a worker, it would be good to have a unique identifier for that worker. Jobs are fixed, depending on the job a salary will be assigned to the employee once it is created.

- Notes:
Please note the following construction details:

The domain must have the full CRUD (Create, Read, Update, Delete), using the associated HTTP verbs.
Create a special HTTP request that looks for employees by job, in addition to all those that create, read, update, or delete employee-type items
Objects will persist only in memory
You need to be aware of good API design practices: Use error codes and answers correctly in case of incorrect invocations
You must add Swagger as a prerequisite for documenting project endpoints.
Level 2




Add one endpoint to upload and another to download each employee's photo.

Level 3

Add a filter to the project to add an "IT-Academy-Exercise" header with a "Simple-Service" value in the response of all endpoints
