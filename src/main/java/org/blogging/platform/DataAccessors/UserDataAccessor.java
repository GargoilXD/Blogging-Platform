package org.blogging.platform.DataAccessors;

import org.blogging.platform.DataTransporters.UserDataTransporter;
import org.blogging.platform.Exceptions.DataAccessException;
import org.blogging.platform.Interfaces.DataAccessor;
import org.blogging.platform.Models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDataAccessor implements DataAccessor<User, UserDataTransporter> {
    enum Queries {
        GET_BY_ID("SELECT * FROM users WHERE id = ?"),
        GET_BY_USERNAME("SELECT * FROM users WHERE username = ?"),
        INSERT("INSERT INTO users (username, password, full_name, email, gender) VALUES (?, ?, ?, ?, ?)");

        final String query;
        Queries(String query) { this.query = query; }
    }
    DataSource dataSource;

    public UserDataAccessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Optional<User> getByID(long id) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_ID.query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()?  Optional.of(User.fromResultSet(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Getting User By ID: %s", id), e);
        }
    }
    public Optional<User> getByUsername(String username) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_USERNAME.query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()? Optional.of(User.fromResultSet(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Getting User By Name: %s", username), e);
        }
    }
    @Override
    public void save(UserDataTransporter transporter) throws DataAccessException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(Queries.INSERT.query)) {
            statement.setLong(1, transporter.getUsername());
            statement.setString(2, transporter.getPassword());
            statement.setString(3, transporter.getFullName());
            statement.setString(4, transporter.getEmail());
            statement.setString(5, transporter.getGender());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format("Error Saving User: %s", transporter.getUsername()), e);
        }
    }
}
