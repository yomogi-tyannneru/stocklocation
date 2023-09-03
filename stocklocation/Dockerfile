FROM amazoncorretto:17 AS build
COPY ./ /home/app
RUN cd /home/app && ./gradlew build -x test

FROM amazoncorretto:17-alpine
COPY --from=build /home/app/build/libs/stocklocation-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","/usr/local/lib/app.jar","--spring.profiles.active=local"]