package org.blogging.platform.DataTransporters;

import org.blogging.platform.Interfaces.DataTransporter;

public class PostDataTransporter implements DataTransporter {
    public enum Key { ID, UserID, Title, Body, isDraft }
    public PostDataTransporter set(Key key, Object value) {
        data.put(key.name(), value);
        return this;
    }
    public long getID() {
        return (long) data.get("ID");
    }
    public long getUserID() {
        return (long) data.get("UserID");
    }
    public String getTitle() {
        return (String) data.get("Title");
    }
    public String getBody() {
        return (String) data.get("Body");
    }
    public boolean getIsDraft() {
        return (boolean) data.get("isDraft");
    }
}
