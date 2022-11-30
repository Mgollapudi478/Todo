
# Todo application

Todo application developed using mainly using spring boot framework and couple of below starters.

- spring-boot-starter-web -> For exposing crud APIs
- spring-boot-starter-data-redis -> For storing data in redis

Below are the instructions to run the project.

- Since it is spring boot project embedded with tomcat, we can start by <b>TodosApplication</b> or use the <b>mvn install</b> to generate the runnable jar
- It persists the data in the redis in the <b>redis://localhost:6379</b>, if the redis deployed in different port or different host, please override the value in the application.properties for a key <b>spring.data.redis.url</b>
- CRUD Apis are
    - Create a TODO for a given user <b>POST</b> http://localhost:8080/users/{userId}/todo
      - Sample input {
            "userId":2,
            "taskId":1,
            "description":"create a first todo of 2nd user",
            "dueDate":"2022-12-15",
            "state":"TODO"
            }
    - Update a TODO for a given user <b>PUT</b> http://localhost:8080/users/{userId}/todo
      - Sample input {
        "userId":2,
        "taskId":1,
        "description":"update first todo of 2nd user",
        "dueDate":"2022-12-16",
        "state":"In Progress"
        }
    - List of TODOs for a given user <b>GET</b> http://localhost:8080/users/{userId}/todos
    - Deletea a TODO for a given user <b>DELETE</b> http://localhost:8080/users/{userId}/todo/{todoId}