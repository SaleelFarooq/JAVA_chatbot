@ECHO OFF
set CLASSPATH=C:\Users\320065416\JAVA_chatbot
javac -classpath .\json.jar  *.java
java -classpath ".\json.jar;C:\Users\320065416\JAVA_chatbot" ChatBot
pause
