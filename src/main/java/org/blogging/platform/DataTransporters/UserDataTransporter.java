package org.blogging.platform.DataTransporters;

import java.util.HashMap;

public class UserDataTransporter implements DataTransporter {
    HashMap<String, Object> data = new HashMap<>();
    public UserDataTransporter setUsername(String Username) {
        data.put("Username", Username);
        return this;
    }
    public UserDataTransporter setPassword(String Password) {
        data.put("Password", Password);
        return this;
    }
    public UserDataTransporter setFullName(String FullName) {
        data.put("FullName", FullName);
        return this;
    }
    public UserDataTransporter setEmail(String Email) {
        data.put("Email", Email);
        return this;
    }
    public UserDataTransporter setGender(String Gender) {
        data.put("Gender", Gender);
        return this;
    }
    public String getUsername() {
        return (String) data.get("Username");
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
