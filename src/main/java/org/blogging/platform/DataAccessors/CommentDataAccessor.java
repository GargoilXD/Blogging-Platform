package org.blogging.platform.DataAccessors;

import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.Exceptions.DataAccessException;
import org.blogging.platform.Interfaces.DataAccessor;
import org.blogging.platform.Models.Comment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDataAccessor implements DataAccessor<Comment, CommentDataTransporter> {
    enum Queries {
        GET_COMMENTS_FOR_POST("SELECT comments.id, postid, userid, body, comments.create_dat, username FROM comments JOIN users ON comments.userid = users.id WHERE comments.postid = ?"),
        INSERT("INSERT INTO comments (postid, userid, body) VALUES (?, ?, ?)"),
        UPDATE("UPDATE comments SET body = ? WHERE id = ?"),
        DELETE("DELETE FROM comments WHERE id = ?");

        final String query;
        Queries(String query) { this.query = query; }
    }
    DataSource dataSource;

    public CommentDataAccessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public ArrayList<Comment> getForPost(long PostID) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_COMMENTS_FOR_POST.query)) {
            statement.setLong(1, PostID);
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Comment> comments = new ArrayList<>();
                while (resultSet.next()) comments.add(Comment.fromResultSet(resultSet));
                return comments;
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Getting Comments For Post: %s", PostID), e);
        }
    }
    @Override
    public void save(CommentDataTransporter transporter) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.INSERT.query)) {
            statement.setLong(1, transporter.getUserID());
            statement.setLong(2, transporter.getPostID());
            statement.setString(3, transporter.getBody());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Saving Comment By ID: %s", transporter.getID()), e);
        }
    }
    @Override
    public void update(CommentDataTransporter transporter) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.UPDATE.query)) {
            statement.setString(1, transporter.getBody());
            statement.setLong(2, transporter.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Updating Comment By ID: %s", transporter.getID()), e);
        }
    }
    @Override
    public void delete(long ID) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.DELETE.query)) {
            statement.setLong(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Deleting Comment By ID: %s", ID), e);
        }
    }
}
