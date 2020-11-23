package com.odysseycorp.homer.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utils to hash and verify.
 */
public class HashingUtils {

    private HashingUtils() {}

    /**
     * Hashes a string.
     *
     * @param toHash the string tgo hash
     * @return the hashed string
     */
    public static String hash(String toHash) {
        return new BCryptPasswordEncoder().encode(toHash);
    }

    /**
     * To verify if a string matches a hash.
     *
     * @param toVerify the string to check in clear
     * @param hash the hash
     * @return if a string matches the hash.
     */
    public static boolean verify(String toVerify, String hash) {
        return new BCryptPasswordEncoder().matches(toVerify, hash);
    }
}
