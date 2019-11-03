package com.floow.app.db;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;

/**
 * Class responsible for Mapping data to MongoDb.
 */

public class MongoMapperService {


    public void saveMappedDataToDB(SortedSet<Map.Entry<String, Integer>> entries, String mongoUrl) {


        try (MongoClient mongoClient = MongoClients.create("mongodb://" + mongoUrl)) {

            MongoDatabase database = mongoClient.getDatabase("test");

            try {
                database.createCollection("DataMapper");
            } catch (MongoCommandException e) {
                System.err.println("Collection exits");
            }

            ArrayList<Document> documents = new ArrayList<>();
            MongoCollection<Document> collection = database.getCollection("DataMapper");

            for (Map.Entry<String, Integer> entry : entries) {
                Document document = new Document();
                document.append("wordId", entry.getKey());
                document.append("count", entry.getValue());
                documents.add(document);

            }
            collection.insertMany(documents);

        }

    }
}
