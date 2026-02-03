# Use Java 17 (lightweight)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy source code, public files, and MySQL connector
COPY src ./src
COPY public ./public
COPY lib ./lib

# Compile Java source files
RUN mkdir -p bin && \
    javac -cp "lib/*" -d bin src/com/JDBC/project/*.java

# Expose port (Railway will inject actual PORT)
EXPOSE 8080

# Run the application
CMD ["java", "-cp", "bin:lib/*", "com.JDBC.project.Main"]
