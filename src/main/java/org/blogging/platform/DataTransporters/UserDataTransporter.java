package org.blogging.platform.DataTransporters;

import org.blogging.platform.Interfaces.DataTransporter;

public class UserDataTransporter implements DataTransporter {
    public enum Key { Username, Password, FullName, Email, Gender }
    public UserDataTransporter set(Key key, Object value) {
        data.put(key.name(), value);
        return this;
    }
    public long getUsername() {
        return (long) data.get("Username");
    }
    public String getPassword() {
        return (String) data.get("Password");
    }
    public String getFullName() {
        return (String) data.get("FullName");
    }
    public String getEmail() {
        return (String) data.get("Email");
    }
    public String getGender() {
        return (String) data.get("Gender");
    }
}
