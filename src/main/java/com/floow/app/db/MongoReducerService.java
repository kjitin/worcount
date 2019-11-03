package com.floow.app.db;

import com.floow.app.ds.Results;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class responsible for finding the Total count of words from Mongodb.
 */

public class MongoReducerService {


    public void executeReduce(String mongoUrl) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://" + mongoUrl)) {

            MongoDatabase database = mongoClient.getDatabase("test");

            MongoCollection<Document> collection = database.getCollection("DataMapper");

            String map = "function() { emit(this.wordId, this.count); } ";
            String reduce = "function(key, values) { return Array.sum(values); }";
            MapReduceIterable<Document> results = collection.mapReduce(
                    map,
                    reduce,
                    Document.class

            );

            ArrayList<Results> resultsArrayList = new ArrayList<>();
            for (Document result: results) {
                Results reducedResults = new Results();
                reducedResults.setWordId(result.get("_id").toString());
                reducedResults.setCount(Double.parseDouble(result.get("value").toString()));
                resultsArrayList.add(reducedResults);
            }

            Collections.reverse(resultsArrayList);

            for (Results sortedResult : resultsArrayList) {
                System.out.println(sortedResult.getWordId() + " "+ sortedResult.getCount());
            }


        }


    }
}
