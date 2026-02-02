package org.blogging.platform.DataTransporters;

import java.util.HashMap;

public class CommentDataTransporter implements DataTransporter {
    HashMap<String, Object> data = new HashMap<>();
    public CommentDataTransporter setID(String ID) {
        data.put("ID", ID);
        return this;
    }
    public CommentDataTransporter setPostID(long PostID) {
        data.put("PostID", PostID);
        return this;
    }
    public CommentDataTransporter setUserID(long UserID) {
        data.put("UserID", UserID);
        return this;
    }
    public CommentDataTransporter setUsername(String Username) {
        data.put("Username", Username);
        return this;
    }
    public CommentDataTransporter setBody(String Body) {
        data.put("Body", Body);
        return this;
    }
    public String getID() {
        return (String) data.get("ID");
    }
    public long getPostID() {
        return (long) data.get("PostID");
    }
    public long getUserID() {
        return (long) data.get("UserID");
    }
    public String getUsername() {
        return (String) data.get("Username");
    }
    public String getBody() {
        return (String) data.get("Body");
    }
}
