package com.floow.app;

import com.floow.app.db.MongoDataReaderService;
import com.floow.app.db.MongoDataService;
import com.floow.app.db.MongoReducerService;

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
        String master = null;

        if (commandOptions.hasOption("-source")) {
            fileName = commandOptions.valueOf("-source");
        }
        if (commandOptions.hasOption("-mongo")) {
            mongoUrl = commandOptions.valueOf("-mongo");
        }
        if (commandOptions.hasOption("-master")) {
            master = commandOptions.valueOf("-master");
        }

        System.out.println("File Name "+ fileName + " mongourl "+ mongoUrl);
        app.readFile(fileName, mongoUrl, master);


    }


    public void readFile(String fileName, String mongoUrl, String master) throws URISyntaxException, IOException {


        if (master != null) {
            Path path = Paths.get(fileName);

            Stream<String> lines = Files.lines(path);
            List<String> data = lines.collect(Collectors.toList());
            MongoDataService mongoDataService = new MongoDataService();

            DataSplitter dataSplitter = new DataSplitter(mongoDataService);
            dataSplitter.split(data, mongoUrl);
        }

        MongoDataReaderService mongoDataReaderService = new MongoDataReaderService();
        mongoDataReaderService.read(mongoUrl);
        MongoReducerService mongoReducerService = new MongoReducerService();
        mongoReducerService.executeReduce(mongoUrl);

    }

}
