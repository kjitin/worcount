package com.floow.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException, IOException {

        App app = new App();
        CommandOptions commandOptions = new CommandOptions(args);
        String fileName = null;
        String mongoUrl = null;

        if (commandOptions.hasOption("-source")) {
            fileName = commandOptions.valueOf("-source");
        }
        if (commandOptions.hasOption("-mongo")) {
            mongoUrl = commandOptions.valueOf("-mongo");
        }

        System.out.println("File Name "+ fileName + " mongourl "+ mongoUrl);
        app.readFile(fileName, mongoUrl);


    }


    public void readFile(String fileName, String mongoUrl) throws URISyntaxException, IOException {


        Path path = Paths.get(fileName);

        Stream<String> lines = Files.lines(path);
        List<String> data = lines.collect(Collectors.toList());
        MongoDataService mongoDataService = new MongoDataService();

        DataSplitter dataSplitter = new DataSplitter(mongoDataService);

        dataSplitter.split(data, mongoUrl);

    }

}
