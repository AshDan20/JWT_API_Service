package com.poc.jwtapi.JWTApiService;

import com.poc.jwtapi.JWTApiService.model.User;
import com.poc.jwtapi.JWTApiService.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.poc.jwtapi.JWTApiService.AppConstants.EXPIRATION_TIME;

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

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Object getInfo(SecurityContextHolder context) {
        System.out.println("*********************** ");
        User authUser = userService.findByUsername(((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return authUser;
    }

    @PostMapping("/auth")
    public Map<String, String> doLogin(@RequestBody(required = false) User user) {
        Map<String, String> response = new HashMap<>();
        String message = null;
        try {
            if(user == null){
                throw new BadCredentialsException("Credentials missing");
            }
            userService.loginUser(user.getUsername(), user.getPassword());
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, AppConstants.SECRET.getBytes("UTF-8"))
                    .compact();
            response.put("token", token);
        }catch (BadCredentialsException | UsernameNotFoundException e){
            message = "Incorrect username or password";
        } catch (UnsupportedEncodingException e) {
            message = "Error while logging in";
        }
        response.put("message", message);
        return response;
    }
}
