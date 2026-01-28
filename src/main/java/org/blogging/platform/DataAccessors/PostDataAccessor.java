package org.blogging.platform.DataAccessors;

import org.blogging.platform.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.PostDataTransporter;
import org.blogging.platform.Interfaces.DataAccessor;
import org.blogging.platform.Models.Post;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDataAccessor implements DataAccessor<Post, PostDataTransporter> {
    enum Queries {
        GET_BY_ID("SELECT * FROM posts WHERE id = ?"),
        GET_BY_TITLE("SELECT * FROM posts WHERE title = ?"),
        GET_ALL("SELECT * FROM posts"),
        INSERT("INSERT INTO posts (userid, title, body, draft) VALUES (?, ?, ?, ?)"),
        UPDATE("UPDATE posts SET title = ?, body = ?, draft = ? WHERE id = ?"),
        DELETE("DELETE FROM posts WHERE id = ?");

        final String query;
        Queries(String query) { this.query = query; }
    }
    DataSource dataSource;

    public PostDataAccessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Optional<Post> getByID(long id) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_ID.query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()? Optional.of(Post.fromResultSet(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Getting Post By ID: %s", id), e);
        }
    }
    public Post getByTitle(String title) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_TITLE.query)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()? Post.fromResultSet(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Getting Post By Title: %s", title), e);
        }
    }
    @Override
    public List<Post> getAll() throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_ALL.query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Post> posts = new ArrayList<>();
                while (resultSet.next()) posts.add(Post.fromResultSet(resultSet));
                return posts;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error Getting All Posts", e);
        }
    }
    @Override
    public void save(PostDataTransporter transporter) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.INSERT.query)) {
            statement.setLong(1, transporter.getUserID());
            statement.setString(2, transporter.getTitle());
            statement.setString(3, transporter.getBody());
            statement.setBoolean(4, transporter.getIsDraft());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Saving Post By ID: %s", transporter.getID()), e);
        }
    }
    @Override
    public void update(PostDataTransporter transporter) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.UPDATE.query)) {
            statement.setString(1, transporter.getTitle());
            statement.setString(2, transporter.getBody());
            statement.setBoolean(3, transporter.getIsDraft());
            statement.setLong(4, transporter.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Updating Post By ID: %s", transporter.getID()), e);
        }
    }
    @Override
    public void delete(long ID) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.DELETE.query)) {
            statement.setLong(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Deleting Post By ID: %s", ID), e);
        }
    }
}
