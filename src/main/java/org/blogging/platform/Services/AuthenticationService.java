package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.UserDataAccessor;
import org.blogging.platform.DataTransporters.UserDataTransporter;
import org.blogging.platform.Exceptions.DataAccessException;
import org.blogging.platform.Models.User;
import org.blogging.platform.Utilities.PasswordHasher;

import javax.sql.DataSource;
import java.util.Optional;

public class AuthenticationService extends Service {
    UserDataAccessor dataAccessor;
    public User CurrentUser = null;

    @Override
    public void Initialize(DataSource dataSource) {
        this.dataAccessor = new UserDataAccessor(dataSource);
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
        dataAccessor.save(transporter);
    }
}
