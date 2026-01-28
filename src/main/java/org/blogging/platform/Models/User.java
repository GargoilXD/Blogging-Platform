package org.blogging.platform.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public long ID;
    public String Username;
    public String Password;
    public String FullName;
    public String Email;
    public String Gender;
    public String CreatedAt;

    public User(long ID, String Username, String Password, String FullName, String Email, String Gender, String CreatedAt) {
        this.ID = ID;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
        this.Email = Email;
        this.Gender = Gender;
        this.CreatedAt = CreatedAt;
    }
    public static User fromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
            resultSet.getLong("id"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("full_name"),
            resultSet.getString("email"),
            resultSet.getString("gender"),
            resultSet.getString("created_at")
        );
    }
}
