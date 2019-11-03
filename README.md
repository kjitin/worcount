The application can be run using the following command on one of the servers as this will enable the server to parse the file and inserted blocks of data in MongoDB

java -Xmx8192m -jar wordcount-1.0.jar -source test.txt -mongo 127.0.0.1:27017 -master yes

For running on additional servers the following command
java -Xmx8192m -jar wordcount-1.0.jar -source test.txt -mongo 127.0.0.1:27017

The application can be compiled using mvn package but the generated jar will be placed in target folder

This servers will only be responsible for getting data from MongoDB and finding the wordcount.
Additional tasks to be done , parse the file to remove . to prevent it being recognised as a different word.
Prevent data to be inserted if it already exits.
Find only the top words , currently it outputs all the words found which will be unncessary load ont the server to display the data for large files

 

