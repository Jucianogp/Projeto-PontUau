FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR da aplicação
COPY target/*.jar app.jar

# Copia o script wait-for-it
COPY wait-for-it.sh wait-for-it.sh

# Dá permissão de execução ao script dentro do container
RUN chmod +x wait-for-it.sh

# Expõe a porta do Spring Boot
EXPOSE 8080

# Usa wait-for-it para esperar o MySQL antes de iniciar a aplicação
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--timeout=30", "--strict", "--", "java", "-jar", "app.jar"]
