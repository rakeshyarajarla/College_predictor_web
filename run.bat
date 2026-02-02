@echo off
if not exist bin mkdir bin
javac -cp "src;lib\mysql-connector-j-9.4.0.jar" -d bin src\com\JDBC\project\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b
)
echo Starting server on port 8000...
java -cp "bin;lib\mysql-connector-j-9.4.0.jar" com.JDBC.project.Main
pause
