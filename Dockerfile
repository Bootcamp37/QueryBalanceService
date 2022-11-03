FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/QueryBalanceService-0.0.1-SNAPSHOT.jar ms-querybalance.jar
ENTRYPOINT ["java","-jar","/ms-querybalance.jar"]