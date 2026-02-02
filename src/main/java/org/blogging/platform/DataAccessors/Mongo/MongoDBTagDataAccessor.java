package org.blogging.platform.DataAccessors.Mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.blogging.platform.DataAccessors.Interfaces.TagDataAccessor;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MongoDBTagDataAccessor extends MongoDBDataAccessor implements TagDataAccessor {
    MongoCollection<Document> collection;

    public MongoDBTagDataAccessor() {
        this.collection = database.getCollection("post_tags");
    }
    @Override
    public List<String> getAllTags() throws DataAccessException {
        try {
            HashSet<String> allTags = new HashSet<>();
            collection.find().forEach(document -> allTags.addAll(document.getList("tags", String.class)));
            return allTags.stream().toList();
        } catch (MongoException e) {
            throw new DataAccessException("Error Getting All Tags", e);
        }
    }
    @Override
    public HashMap<Long, List<String>> getAllTagsByPosts() throws DataAccessException {
        try {
            HashMap<Long, List<String>> tagsByPost = new HashMap<>();
            collection.find().forEach(document -> {
                Long PostID = document.getLong("_id");
                List<String> tags = document.getList("tags", String.class);
                tagsByPost.put(PostID, tags);
            });
            return tagsByPost;
        } catch (MongoException e) {
            throw new DataAccessException("Error Getting Tags By Post", e);
        }
    }
    @Override
    public List<String> getTagsForPost(long PostID) throws DataAccessException {
        try {
            ArrayList<String> tags = new ArrayList<>();
            Document postTags = collection.find(Filters.eq("_id", PostID)).first();
            if (postTags != null) {
                List<String> tagsList = postTags.getList("tags", String.class);
                if (tagsList != null) tags.addAll(tagsList);
            }
            return tags;
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Getting Tags For Post: %s", PostID), e);
        }
    }
    @Override
    public void setPostTags(long postID, List<String> tags) throws DataAccessException {
        try {
            collection.updateOne(
                    Filters.eq("_id", postID),
                    Updates.set("tags", tags),
                    new UpdateOptions().upsert(true)
            );
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Setting Post Tags: %s", postID), e);
        }
    }
}
