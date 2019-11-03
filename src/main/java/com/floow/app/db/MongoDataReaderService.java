package com.floow.app.db;

import com.floow.app.WordSplitter;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Class responsible for getting data from Mongodb based on what is saved from file in DB
 */

public class MongoDataReaderService {



    public void read (String mongoUrl) {

        WordSplitter wordSplitter = new WordSplitter();

        try (MongoClient mongoClient = MongoClients.create("mongodb://" + mongoUrl)) {

            MongoDatabase database = mongoClient.getDatabase("test");

            MongoCollection<Document> collection = database.getCollection("DataFeed");

            MongoCursor<Document> read = collection.find(and(eq("read", false)))
                    .projection(Projections.fields(Projections.include("feed"), Projections.excludeId()))
                    .iterator();

            while (read.hasNext()) {

                Document next = read.next();
                List<String> feed = next.getList("feed", String.class);
                wordSplitter.splitWords(feed, mongoUrl);
            }


        }
    }
}
