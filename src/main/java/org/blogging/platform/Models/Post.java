package org.blogging.platform.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    public long ID;
    public long UserID;
    public String Username;
    public String Title;
    public String Body;
    public boolean IsDraft;
    public String CreatedAt;

    public Post(long ID, long UserID, String Username, String Title, String Body, boolean IsDraft, String CreatedAt) {
        this.ID = ID;
        this.UserID = UserID;
        this.Username = Username;
        this.Title = Title;
        this.Body = Body;
        this.IsDraft = IsDraft;
        this.CreatedAt = CreatedAt;
    }
    public static Post fromResultSet(ResultSet resultSet) throws SQLException {
        return new Post(
            resultSet.getLong("id"),
            resultSet.getLong("user_id"),
            resultSet.getString("username"),
            resultSet.getString("title"),
            resultSet.getString("body"),
            resultSet.getBoolean("is_draft"),
            resultSet.getString("created_at")
        );
    }
}
