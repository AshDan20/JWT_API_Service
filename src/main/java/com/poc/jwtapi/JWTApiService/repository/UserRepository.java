package com.poc.jwtapi.JWTApiService.repository;

import com.poc.jwtapi.JWTApiService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


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

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}