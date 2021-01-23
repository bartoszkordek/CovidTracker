# CovidTracker
Celem projektu jest zaimplementowanie "Systemu do zbierania i manipulowania danych z systemów zewnętrzych w czasie rzeczywistym"
używając architektury mikroserwisów.

Projekt został napisany i był testowany przy użyciu Java 11.

## Steps to run
(dla systemu Windows proszę zastąpić wywołania `./gradlew` wywołaniami `gradlew.bat`)

1. Zbudowanie obrazów Dockerowych dla wszystkich mikroserwisów
    ```shell script
    ./gradlew dockerBuildImage
    ```
1. Uruchomienie obrazów Dockerowych mikroserwisów w trybie Docker Swarm
    ```shell script
    docker swarm init --advertise-addr 172.17.0.1 --listen-addr 0.0.0.0
    docker stack deploy --compose-file docker-compose.yaml covid-tracker
    ```
   
## Zatrzymywanie
Sprawdzenie czy sieć jest uruchomiona
```shell script
docker stack services covid-tracker
```
Zatrzymanie wszystkich serwisów
```shell script
docker stack rm covid-tracker
```

## Dostęp do serwisów
1. Eureka: http://localhost:8010
1. RabbitMQ: http://localhost:15672 (user: guest, pass: guest)
1. Kibana: http://localhost:5601
1. Zipkin: http://localhost:9411
1. H2-Console http://localhost:8011/account/h2-console/ (H2-Embedded JDBC URL: jdbc:h2:mem:testdb, user: covid, password: covid)

## Dokumentacja
Dokumentacja projektu jest dostępna w sekcji [Wiki](https://github.com/bartoszkordek/CovidTracker/wiki).
