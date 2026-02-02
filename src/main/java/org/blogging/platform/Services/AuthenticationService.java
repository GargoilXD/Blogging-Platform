package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.Interfaces.UserDataAccessor;
import org.blogging.platform.DataTransporters.UserDataTransporter;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.User;
import org.blogging.platform.Utilities.PasswordHasher;

import java.util.Optional;

public class AuthenticationService {
    UserDataAccessor dataAccessor;
    public User CurrentUser = null;

    public AuthenticationService(UserDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    public boolean login(String username, String password) throws DataAccessException {
        Optional<User> optional = dataAccessor.getByUsername(username);
        if (optional.isEmpty()) return false;
        User user = optional.get();
        if (!PasswordHasher.verifyPassword(password, user.Password)) return false;
        CurrentUser = user;
        return true;
    }
    public void register(UserDataTransporter transporter) throws DataAccessException {
        transporter.setPassword(PasswordHasher.hashPassword(transporter.getPassword()));
        dataAccessor.register(transporter);
    }
}
