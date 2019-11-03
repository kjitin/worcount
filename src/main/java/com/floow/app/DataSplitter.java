package com.floow.app;

import java.util.ArrayList;
import java.util.List;

public class DataSplitter {


    public static int LINESPERSPLIT = 5;

    private MongoDataService mongoDataService;
    public DataSplitter(MongoDataService mongoDataService) {
        this.mongoDataService = mongoDataService;
    }

    public void split(List<String> inputContent, String mongoUrl) {

        System.out.println("File contains "+ inputContent.size() + "lines");

        ArrayList<String> blocks = new ArrayList<>();

        for (int i=0; i< inputContent.size(); i++) {
            String line = inputContent.get(i);

            blocks.add(line);

            if (((i+1) % LINESPERSPLIT) ==0 || i == inputContent.size() -1) {
               mongoDataService.save(blocks, mongoUrl);
               blocks = new ArrayList<>();
            }
        }
    }
}
