package org.blogging.platform.Models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.Instant;

public class Comment {
    @BsonId
    private String ID;
    @BsonProperty("user_id")
    private long UserID;
    @BsonProperty("post_id")
    private long PostID;
    @BsonProperty("username")
    private String Username;
    @BsonProperty("body")
    private String Body;
    @BsonProperty("created_at")
    private Instant CreatedAt;

    public Comment() {}
    public Comment(String ID, long UserID, long PostID, String Username, String Body, Instant CreatedAt) {
        this.ID = ID;
        this.UserID = UserID;
        this.PostID = PostID;
        this.Username = Username;
        this.Body = Body;
        this.CreatedAt = CreatedAt;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setUserID(long UserID) {
        this.UserID = UserID;
    }
    public void setPostID(long PostID) {
        this.PostID = PostID;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public void setBody(String body) {
        Body = body;
    }
    public void setCreatedAt(Instant createdAt) {
        CreatedAt = createdAt;
    }
    public String getID() {
        return ID;
    }
    public long getUserID() {
        return UserID;
    }
    public long getPostID() {
        return PostID;
    }
    public String getUsername() {
        return Username;
    }
    public String getBody() {
        return Body;
    }
    public Instant getCreatedAt() {
        return CreatedAt;
    }
}
