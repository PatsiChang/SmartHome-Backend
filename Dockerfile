FROM openjdk:17
WORKDIR /boot
COPY ./target/RecipeGenerator-0.0.1-SNAPSHOT.jar/
EXPOSE 8080
CMD ["java", "-jar", "RecipeManagement"]