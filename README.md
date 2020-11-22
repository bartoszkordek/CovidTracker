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
1. Run all microservices in docker containers
    ```shell script
    ./gradlew composeUp
    ```
   
## Clean
After playing, clean up after yourself with
```shell script
./gradlew composeDown
```

If you're in a hurry, try instead
```shell script
docker rm -f $(docker ps -aq)
```