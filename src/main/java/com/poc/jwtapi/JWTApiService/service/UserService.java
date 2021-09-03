package com.poc.jwtapi.JWTApiService.service;

import com.poc.jwtapi.JWTApiService.model.User;
import com.poc.jwtapi.JWTApiService.repository.UserRepository;
import com.poc.jwtapi.JWTApiService.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * Class for
 * <br>
 * <br>
 *
 * @author AshishD
 * @since date
 * -------------------------------------------------------------------
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public User save(User user) {
        user.setPassword(EncoderUtil.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void loginUser(String username, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (!usernamePasswordAuthenticationToken.isAuthenticated()) {
            throw new BadCredentialsException("Invalid password");
        }
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    public void init(){
        User testUser = userRepository.findByUsername("test");
        if(testUser == null){
            // Add seed data
            List<User> seedUsers = new ArrayList<>();
            seedUsers.add(new User("test", "098f6bcd4621d373cade4e832627b4f6"));
            seedUsers.add(new User("admin", "1a1dc91c907325c69271ddf0c944bc72", "ADMIN"));
            userRepository.saveAll(seedUsers);
        }
    }
}