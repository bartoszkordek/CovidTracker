# CovidTracker
Project of Covid Tracker system based on microservice architecture

Project was built and tested using Java 11.

## Steps to run
(for Windows substitute `./gradlew` with `gradlew.bat`)

1. Clean old files and build the project
    ```shell script
    ./gradlew clean build
    ```
1. Build docker images of all microservices
    ```shell script
    ./gradlew dockerBuildImage
    ```
1. Run all microservices in Docker Swarm mode
    ```shell script
    docker swarm init --advertise-addr 172.17.0.1 --listen-addr 0.0.0.0
    docker stack deploy --compose-file docker-compose.yaml covid-tracker
    ```
   
## Tear down
Check if the network is running
```shell script
docker stack services covid-tracker
```
After playing, clean up after yourself with
```shell script
docker stack rm covid-tracker
```

If you're in a hurry, try instead
```shell script
docker rm -f $(docker ps -aq)
```

## Access services
1. Eureka: http://localhost:8010
1. RabbitMQ: http://localhost:15672 (user: guest, pass: guest)
1. Kibana: http://localhost:5601
1. Zipkin: http://localhost:9411

## Dokumentacja
Dokumentacja projektu jest dostępna w sekcji [Wiki](https://github.com/bartoszkordek/CovidTracker/wiki).