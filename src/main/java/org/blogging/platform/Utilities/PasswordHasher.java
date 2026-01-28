package org.blogging.platform.Utilities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHasher {
    private static final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 32);

    public static String hashPassword(String password) {
        return argon2.hash(3, 65536, 4, password.toCharArray());
    }
    public static boolean verifyPassword(String password, String storedHash) {
        return argon2.verify(storedHash, password.toCharArray());
    }
}
