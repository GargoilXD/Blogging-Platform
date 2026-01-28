package org.blogging.platform.DataTransporters;

import org.blogging.platform.Interfaces.DataTransporter;

public class CommentDataTransporter implements DataTransporter {
    public enum Key { ID, PostID, UserID, Body }
    public CommentDataTransporter set(Key key, Object value) {
        data.put(key.name(), value);
        return this;
    }
    public long getID() {
        return (long) data.get("ID");
    }
    public long getPostID() {
        return (long) data.get("PostID");
    }
    public long getUserID() {
        return (long) data.get("UserID");
    }
    public String getBody() {
        return (String) data.get("Body");
    }
}
