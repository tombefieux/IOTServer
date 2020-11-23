package com.odysseycorp.homer.services;

import com.odysseycorp.homer.models.User;
import com.odysseycorp.homer.repositories.UserRepository;
import com.odysseycorp.homer.utils.HashingUtils;
import com.odysseycorp.homer.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Here is the trick to inject the current user given by the token in the request.
     *
     * @return the current user for the request.
     */
    @Bean(name = "currentUser")
    @RequestScope
    public User requestCurrentUser() {
        // get request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            final String requestTokenHeader = request.getHeader("Authorization");
            final String token = requestTokenHeader.substring(7); // remove "Bearer "
            final String id = JwtUtils.getIdInToken(token);
            return this.getUserById(id); // get and return user
        }
        LOGGER.debug("Get current user not called in the context of an HTTP request");
        return null;
    }

    /**
     * Gets a user by it's id
     *
     * @param id the id
     * @return the user or null if doesn't exist
     */
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /**
     * Gets a user by it's username
     *
     * @param username the username
     * @return the user or null if doesn't exist
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Checks the password of a user.
     *
     * @param id the id to find the user
     * @param passwd the password in clear
     * @return if the password is ok or false otherwise
     */
    public boolean mapUserFromDB(String id, String passwd) {
        User user = this.getUserById(id);
        if (user == null) {
            return false;
        }
        return HashingUtils.verify(passwd, user.getPassword());
    }
}