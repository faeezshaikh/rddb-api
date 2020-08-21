# RDDB Writeback Service

A Java Spring Boot Microservice that listens to a SQS queue and writes back to RDDB (Oracle). 

The service reads from a Messaging queue & writes to an Oracle database in AWS.

The service is containerized using Docker and is hosted in ECS.

&nbsp;

### To purge a SQS queue:

aws sqs purge-queue --queue-url https://sqs.us-east-1.amazonaws.com/635283063535/verde-q

Pushing a change

### Architecture : 

[![ECS Arch](https://test-aws-vers1.s3.amazonaws.com/InstrumentService+(1).png)](https://test-aws-vers1.s3.amazonaws.com/InstrumentService+(1).png)
&nbsp;

## Instructions

#### To build

```
./gradlew clean build
```

#### To run unit tests:

```java
./gradlew test
```

#### To run code coverage report:
```
./gradlew jacocoTestReport
```

(Reports will be generated under /build/reports/jacoco)


&nbsp;
     
     
## Dockerize

#### To build a docker image:

```java
./gradlew build docker
```

#### To run the containerized service locally:

```java
docker run -p 8080:8080 -t bmx/rddb-writeback-service 
```

#### To authenticate docker with ECR
```
aws ecr get-login --no-include-email
```

Then run the command thats the output of the previous command

#### To tag docker image:
```
docker tag bmx/rddb-writeback-service 635283063535.dkr.ecr.us-east-1.amazonaws.com/rddb-writeback-service
```

#### To upload image to ECR:
```
docker push 635283063535.dkr.ecr.us-east-1.amazonaws.com/rddb-writeback-service
```

## Health Check

```java
http://localhost:8081/actuator/health
```

## Service Info:
```java
http://localhost:8081/actuator/info
```



Once the service is up and running test the following endpoints:

- Helper endpoint to get count of readings for a given "noDispoTest" in the READINGS table in RDDB

```java
HTTP GET
  http://localhost:8081/api/v1/readingscount/{noDispoTest}
```

Two listeners come up. One listener for "Instruments" and other for "Card readings"

1. To Test 'Card reading' writeback:

### To create readings and trigger the RDDB writeback service: (run under card-reading dir)


```java
curl -H "Content-Type: application/json" -d '@src/test/resources/readings.json' -X PUT localhost:8081/card-reading-service/api/readings
```
OR 

```java
curl -H "Content-Type: application/json" -d '@src/test/resources/readings.json' -X PUT ussd-sb-ecs-load-balancer-1700541807.us-east-1.elb.amazonaws.com/card-reading-service/api/readings
```


### To establish SSH tunnel locally to Oracle RDDB database in Sandbox AWS RDS:

```java
ssh -L 1521:bmx-rddb-new.cb56ddv1apkl.us-east-1.rds.amazonaws.com:1521 ec2-user@ec2-100-26-2-103.compute-1.amazonaws.com -i Bastion.pem
```

And use spring_profile_active = local







