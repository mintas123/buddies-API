package buddiesapi.domain.account

import java.security.SecureRandom
import org.springframework.security.crypto.bcrypt.BCrypt

object PasswordHasher {
    /**
     * Return hash of the password
     */
    fun hashPassword(plainTextPassword: String): String {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt())
    }

    /**
     * Return information whether string matches saved hash of a password
     */
    fun checkPass(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }

    /**
     * Return random 16-char strong password for resetting purposes
     */
    fun generatePassword(): String = (1..16)
        .map { SecureRandom().nextInt(33, 123).toChar() }
        .toString()

}
