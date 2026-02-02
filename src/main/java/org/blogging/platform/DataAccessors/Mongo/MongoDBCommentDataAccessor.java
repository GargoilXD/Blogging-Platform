package org.blogging.platform.DataAccessors.Mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.blogging.platform.DataAccessors.Interfaces.CommentDataAccessor;
import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.Comment;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MongoDBCommentDataAccessor extends MongoDBDataAccessor implements CommentDataAccessor {
    MongoCollection<Comment> collection;

    public MongoDBCommentDataAccessor() {
        this.collection = database.getCollection("comments", Comment.class);
    }
    @Override
    public List<Comment> getForPost(long PostID) throws DataAccessException {
        try {
            List<Comment> comments = new ArrayList<>();
            collection.find(Filters.eq("postID", PostID)).into(comments);
            return comments;
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Getting Comments For Post: %s", PostID), e);
        }
    }
    @Override
    public void create(CommentDataTransporter transporter) throws DataAccessException {
        try {
            collection.insertOne(new Comment(new ObjectId().toHexString(), transporter.getUserID(), transporter.getPostID(), transporter.getUsername(), transporter.getBody(), Instant.now()));
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Saving Comment By User: %s", transporter.getUsername()), e);
        }
    }
    @Override
    public void update(CommentDataTransporter transporter) throws DataAccessException {
        try {
            UpdateResult result = collection.updateOne(Filters.eq("iD", transporter.getID()), Updates.set("body", transporter.getBody()));
            if (result.getModifiedCount() == 0) throw new DataAccessException("No Data was Updated");
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Updating Comment By ID: %s", transporter.getID()), e);
        }
    }
    @Override
    public void delete(String ID) throws DataAccessException {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("iD", ID));
            if (result.getDeletedCount() == 0) throw new DataAccessException("No Data was Deleted");
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Deleting Comment By ID: %s", ID), e);
        }
    }
    @Override
    public void deleteForPost(long PostID) throws DataAccessException {
        try {
            collection.deleteMany(Filters.eq("postID", PostID));
        } catch (MongoException e) {
            throw new DataAccessException(String.format("Error Deleting Comments For Post: %s", PostID), e);
        }
    }
}
