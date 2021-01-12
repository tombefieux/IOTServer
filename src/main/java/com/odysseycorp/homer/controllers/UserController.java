package com.odysseycorp.homer.controllers;

import com.odysseycorp.homer.models.User;
import com.odysseycorp.homer.services.UserService;
import com.odysseycorp.homer.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller to manage users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * To get the current user
     *
     * @return the current user
     */
    @GetMapping
    public User getCurrent() {
        // get request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            final String requestTokenHeader = request.getHeader("Authorization");
            final String token = requestTokenHeader.substring(7); // remove "Bearer "
            final String id = JwtUtils.getIdInToken(token);
            return userService.getUserById(id);
        }
        return null;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String userId){
        return userService.getUserById(userId);
    }

    /**
     * To create a new user
     *
     * @param user the user to add
     * @return empty response
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public void updateUser(@RequestBody User updatedUser){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            final String requestTokenHeader = request.getHeader("Authorization");
            final String token = requestTokenHeader.substring(7); // remove "Bearer "
            final String id = JwtUtils.getIdInToken(token);
            this.userService.updateUser(id, updatedUser);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId){
        this.userService.deleteUser(userId);
    }

}
