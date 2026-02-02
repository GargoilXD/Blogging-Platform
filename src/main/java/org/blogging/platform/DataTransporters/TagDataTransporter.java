package org.blogging.platform.DataTransporters;

import java.util.HashMap;

public class TagDataTransporter implements DataTransporter {
    HashMap<String, Object> data = new HashMap<>();
    public TagDataTransporter setID(long ID) {
        data.put("ID", ID);
        return this;
    }
    public TagDataTransporter setPostID(long PostID) {
        data.put("PostID", PostID);
        return this;
    }
    public TagDataTransporter setName(String Name) {
        data.put("Name", Name);
        return this;
    }
    public String getID() {
        return (String) data.get("ID");
    }
    public long getPostID() {
        return (long) data.get("PostID");
    }
    public String getName() {
        return (String) data.get("Name");
    }
}
