package com.poc.jwtapi.JWTApiService;

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

public interface AppConstants {
    String SECRET = "secret";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}