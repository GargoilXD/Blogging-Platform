package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.UserDataAccessor;
import org.blogging.platform.DataTransporters.UserDataTransporter;
import org.blogging.platform.Models.User;
import org.blogging.platform.Utilities.PasswordHasher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    static class FakeUserDataAccessor implements UserDataAccessor {
        Optional<User> user;
        UserDataTransporter registered = null;

        FakeUserDataAccessor(Optional<User> user) { this.user = user; }

        @Override
        public Optional<User> getByID(Long id) throws DataAccessException { return Optional.empty(); }

        @Override
        public Optional<User> getByUsername(String username) throws DataAccessException { return user; }

        @Override
        public void register(UserDataTransporter transporter) throws DataAccessException { this.registered = transporter; }
    }

    @Test
    @DisplayName("login succeeds with correct credentials")
    void testLoginSuccess() throws DataAccessException {
        long start = System.nanoTime();
        User u = new User(1, "alice", PasswordHasher.hashPassword("password123"), "Alice", "a@x", "F", "now");
        FakeUserDataAccessor fake = new FakeUserDataAccessor(Optional.of(u));
        AuthenticationService svc = new AuthenticationService(fake);

        assertTrue(svc.login("alice", "password123"));
        assertSame(u, svc.CurrentUser);

        long end = System.nanoTime();
        System.out.printf("AuthenticationServiceTest#testLoginSuccess took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("login fails with wrong password")
    void testLoginWrongPassword() throws DataAccessException {
        long start = System.nanoTime();
        User u = new User(1, "bob", PasswordHasher.hashPassword("secret"), "Bob", "b@x", "M", "now");
        FakeUserDataAccessor fake = new FakeUserDataAccessor(Optional.of(u));
        AuthenticationService svc = new AuthenticationService(fake);

        assertFalse(svc.login("bob", "badpass"));
        assertNull(svc.CurrentUser);

        long end = System.nanoTime();
        System.out.printf("AuthenticationServiceTest#testLoginWrongPassword took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("login fails when user not found")
    void testLoginUserNotFound() throws DataAccessException {
        long start = System.nanoTime();
        FakeUserDataAccessor fake = new FakeUserDataAccessor(Optional.empty());
        AuthenticationService svc = new AuthenticationService(fake);

        assertFalse(svc.login("noone", "whatever"));
        assertNull(svc.CurrentUser);

        long end = System.nanoTime();
        System.out.printf("AuthenticationServiceTest#testLoginUserNotFound took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("register hashes password before storing")
    void testRegisterHashesPassword() throws DataAccessException {
        long start = System.nanoTime();
        FakeUserDataAccessor fake = new FakeUserDataAccessor(Optional.empty());
        AuthenticationService svc = new AuthenticationService(fake);

        UserDataTransporter t = new UserDataTransporter().setUsername("jane").setPassword("plainpass");
        svc.register(t);

        assertNotNull(fake.registered);
        String stored = fake.registered.getPassword();
        assertNotEquals("plainpass", stored);
        assertTrue(PasswordHasher.verifyPassword("plainpass", stored));

        long end = System.nanoTime();
        System.out.printf("AuthenticationServiceTest#testRegisterHashesPassword took %d ms%n", (end-start)/1_000_000);
    }
}
