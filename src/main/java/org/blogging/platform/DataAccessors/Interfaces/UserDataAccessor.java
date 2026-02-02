package org.blogging.platform.DataAccessors.Interfaces;

import org.blogging.platform.DataTransporters.UserDataTransporter;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.User;

import java.util.Optional;

public interface UserDataAccessor {
    Optional<User> getByID(Long id) throws DataAccessException;
    Optional<User> getByUsername(String username) throws DataAccessException;
    void register(UserDataTransporter transporter) throws DataAccessException;
}
