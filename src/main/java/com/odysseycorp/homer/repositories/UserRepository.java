package com.odysseycorp.homer.repositories;

import com.odysseycorp.homer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User repository.
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * Finds a user with its username.
     *
     * @param username the username address
     * @return the user if it's found ot null otherwise
     */
    User findByUsername(String username);
}
