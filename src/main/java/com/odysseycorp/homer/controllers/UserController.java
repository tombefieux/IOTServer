package com.odysseycorp.homer.controllers;

import com.odysseycorp.homer.models.User;
import com.odysseycorp.homer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * To get the users
     *
     * @return the users
     */
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
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

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User updatedUser){
        this.userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId){
        this.userService.deleteUser(userId);
    }

}
