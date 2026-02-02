package org.blogging.platform.DatabaseInitializer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class MongoDBDatabaseInitializer {
    public static MongoDatabase initializeDatabase(MongoClient mongoClient) {
        boolean exists = false;
        MongoIterable<String> databases = mongoClient.listDatabaseNames();
        for (String name : databases) {
            if (name.equals("blog")) {
                exists = true;
                break;
            }
        }
        MongoDatabase database = mongoClient.getDatabase("blog");
        if (!exists) {
            database.getCollection("init").insertOne(new Document("init", true));
            System.out.println("MongoDB Database Created");
        }
        return database;
    }
}
