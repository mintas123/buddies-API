package pl.mroz.buddiesapi.domain.account;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;

public class PasswordHasher {
    /**
     * Return hash of the password
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Return information whether string matches saved hash of a password
     */
    public static boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    /**
     * Return random 16-char strong password for resetting purposes
     */
    public static String generatePassword() {
        // 33 == !
        // 122 == Z
        // to include all letters, numbers and special characters we need values between those two
        return new SecureRandom().ints(16, 33, 123)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
