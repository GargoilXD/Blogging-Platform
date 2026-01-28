package org.blogging.platform.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    public long ID;
    public long UserID;
    public String Username;
    public String Title;
    public String Body;
    public boolean Draft;
    public String CreatedAt;

    public Post(long ID, long UserID, String Username, String title, String body, boolean draft, String createdAt) {
        this.ID = ID;
        this.UserID = UserID;
        this.Username = Username;
        this.Title = title;
        this.Body = body;
        this.Draft = draft;
        this.CreatedAt = createdAt;
    }
    public static Post fromResultSet(ResultSet resultSet) throws SQLException {
        return new Post(
            resultSet.getLong("id"),
            resultSet.getLong("userid"),
            resultSet.getString("username"),
            resultSet.getString("title"),
            resultSet.getString("body"),
            resultSet.getBoolean("draft"),
            resultSet.getString("created_at")
        );
    }
}
