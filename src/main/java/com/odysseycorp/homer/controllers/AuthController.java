package com.odysseycorp.homer.controllers;

import com.odysseycorp.homer.models.User;
import com.odysseycorp.homer.models.requests.AuthRequest;
import com.odysseycorp.homer.models.responses.AuthResponse;
import com.odysseycorp.homer.services.UserService;
import com.odysseycorp.homer.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets a JWT token for a user
     *
     * @param request contains the password and username of the user (passed in a JSON form)
     * @return the JWT if everything is OK
     */
    @PostMapping(value = {"/auth"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        // check if user is OK
        if(userService.mapUserFromDB(request.getUsername(), request.getPassword())) {
            User user = userService.getUserByUsername(request.getUsername());
            final String token = JwtUtils.createToken(user.getId(), user.getPassword());
            return ResponseEntity.ok().body(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}