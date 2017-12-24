package com.car.fayoum.utils;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtils {
    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int ITERATIONS = 4;


    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt();
        String hashed_password = BCrypt.hashpw(plainPassword, salt);

        return hashed_password;
    }
    public static boolean checkPassword(String plainPassword, String storedHash) {
        boolean password_verified = false;

        if(null == storedHash || !storedHash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(plainPassword, storedHash);

        return password_verified;
    }
}