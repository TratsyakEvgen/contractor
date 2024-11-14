FROM openjdk:21

WORKDIR /contractor

COPY build/libs/contractor-0.0.1.jar ./contractor.jar

CMD ["java","-jar","contractor.jar"]