package com.odysseycorp.homer.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Utils class for JWT.
 */
public class JwtUtils {

    private JwtUtils() {}

    /**
     * Creates a token with a id and hashed password
     *
     * @param id the id of the user
     * @param userPassword the user hashed password (like in DB)
     * @return the JWT token for this user
     */
    public static String createToken(String id, String userPassword) {
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("id", id);

        JwtBuilder builder = Jwts.builder()
                .setClaims(tokenData)
                .signWith(SignatureAlgorithm.HS256, userPassword);

        return builder.compact();
    }

    /**
     * To verify a token validity. Returns the claims if OK or throw an exception.
     *
     * @param token the token
     * @param password the hash password used to signed the token
     * @return the claims of the token
     * @throws Exception if the token is not valid
     */
    public static Jws<Claims> validateJwtToken(String token, String password) throws Exception {
        return Jwts.parser().setSigningKey(password).parseClaimsJws(token);
    }

    /**
     * Gets the id contained in a token.
     *
     * @param token the token
     * @return the id in the token
     * @throws IllegalArgumentException if there's no id in the token
     */
    public static String getIdInToken(String token) throws IllegalArgumentException {
        final String decodedPlayLoad = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
        JsonObject playLoadJson = JsonParser.parseString(decodedPlayLoad).getAsJsonObject();
        if(!playLoadJson.has("id")) {
            throw new IllegalArgumentException();
        }
        return playLoadJson.get("id").getAsString();
    }
}