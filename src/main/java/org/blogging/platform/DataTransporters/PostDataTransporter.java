package org.blogging.platform.DataTransporters;

import java.util.HashMap;

public class PostDataTransporter implements DataTransporter {
    HashMap<String, Object> data = new HashMap<>();
    public PostDataTransporter setID(long ID) {
        data.put("ID", ID);
        return this;
    }
    public PostDataTransporter setUserID(long UserID) {
        data.put("UserID", UserID);
        return this;
    }
    public PostDataTransporter setTitle(String Title) {
        data.put("Title", Title);
        return this;
    }
    public PostDataTransporter setBody(String Body) {
        data.put("Body", Body);
        return this;
    }
    public PostDataTransporter setIsDraft(boolean IsDraft) {
        data.put("IsDraft", IsDraft);
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
        return ((Boolean) data.get("IsDraft")) == true;
    }
}
