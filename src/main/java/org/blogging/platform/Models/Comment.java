package org.blogging.platform.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {
    public long ID;
    public long UserID;
    public long PostID;
    public String Username;
    public String Body;
    public String CreatedAt;

    public Comment(long ID, long UserID, long PostID, String Username, String Body, String CreatedAt) {
        this.ID = ID;
        this.UserID = UserID;
        this.PostID = PostID;
        this.Username = Username;
        this.Body = Body;
        this.CreatedAt = CreatedAt;
    }
    public static Comment fromResultSet(ResultSet resultSet) throws SQLException {
        return new Comment(
            resultSet.getLong("id"),
            resultSet.getLong("userid"),
            resultSet.getLong("postid"),
            resultSet.getString("username"),
            resultSet.getString("body"),
            resultSet.getString("created_at")
        );
    }
}
