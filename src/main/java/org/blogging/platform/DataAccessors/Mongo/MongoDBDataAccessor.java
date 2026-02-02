package org.blogging.platform.DataAccessors.Mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.blogging.platform.DataSourceFactory.MongoDataSourceFactory;
import org.blogging.platform.DatabaseInitializer.MongoDBDatabaseInitializer;

public abstract class MongoDBDataAccessor {
    protected static final MongoClient mongoClient = MongoDataSourceFactory.createDataSource();
    protected static final MongoDatabase database = MongoDBDatabaseInitializer.initializeDatabase(mongoClient);
    public static void close() {
        mongoClient.close();
    }
}
