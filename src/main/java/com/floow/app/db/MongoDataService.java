package com.floow.app.db;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Class responsible for storing data from File to DB
 */

public class MongoDataService {


    public  void save(ArrayList<String> blocks, String mongoUrl) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://" + mongoUrl)) {

            MongoDatabase database = mongoClient.getDatabase("test");

            try {

                database.createCollection("DataFeed");
            } catch (MongoCommandException e) {
                System.err.println("Collection exits");
            }

            ArrayList<Document> documents = new ArrayList<>();
            MongoCollection<Document> collection = database.getCollection("DataFeed");

            Document document = new Document();
            document.append("feed", blocks);
            document.append("read", false);
            documents.add(document);
            collection.insertMany(documents);


        }

    }
}
