# Javeke Web Service

This is a web service that will be used for my regularly used services and automated tasks.

This microservice exposes a Date API, a Food API and a Web Socket API for communicating IoT devices.
This Web Socket API allows for realtime communication with IoT devices by sending 
control signals to and receiving data signals from the devices.

## Docker Install

This is project has been dockerized and the package is stored on Docker Hub
with name, javeke-ws. 

Run this command to pull the image <br>
`docker pull javeke/javeke-ws:0.1.0`

In order to run the application, it will require an external database
that it is expecting by passing environment variables at runtime.

Required environment variables
- SPRING_DATA_MONGODB_URI
- SPRING_DATA_MONGODB_DATABASE
- SERVER_PORT

Run this command to start the container from the docker image <br>
`docker run -e SPRING_DATA_MONGODB_URI=<your_database_uri> \
-e SPRING_DATA_MONGODB_DATABASE=<your_database_name> \
-e SERVER_PORT=<desired_server_port> \
-p <external_port_mapping>:<desired_server_port>
javeke/javeke-ws:0.1.0`