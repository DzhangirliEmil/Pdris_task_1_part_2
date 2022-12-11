FROM openjdk:19-alpine
ADD out/artifacts/pdris_task1_second_part_jar/pdris_task1_second_part.jar task_getter.jar
EXPOSE 27017
ENTRYPOINT ["java", "-jar", "/task_getter.jar"]