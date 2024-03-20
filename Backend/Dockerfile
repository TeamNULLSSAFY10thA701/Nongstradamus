FROM azul/zulu-openjdk:17 AS builder

RUN mkdir app
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar
RUN mv ./build/libs/*-SNAPSHOT.jar ./

FROM azul/zulu-openjdk:17
WORKDIR /app
COPY --from=builder /app /app
CMD ["java","-jar", "nongstradamus-0.0.1-SNAPSHOT.jar"]
