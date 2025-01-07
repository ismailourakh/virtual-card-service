# Etape 1 : Utiliser l'image officielle de Java
FROM openjdk:17-jdk-slim

# Etape 2 : Définir le repertoire de travail
WORKDIR /app

# Etape 3 : Copier le fichier JAR de l'application
COPY target/virtual-card-service-0.0.1-SNAPSHOT.jar app.jar

# Etape 4 : Exposer le port 8080 (ou celui que vous utilisez)
EXPOSE 8080

# Etape 5 : Commande pour executer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
