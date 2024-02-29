package dev.bogdanbalalau.onlinestorebackend.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(encryptedPassword, password);
    }
}
