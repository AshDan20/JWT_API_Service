package com.poc.jwtapi.JWTApiService.filter;

import com.poc.jwtapi.JWTApiService.AppConstants;
import io.jsonwebtoken.Jwts;
import org.jboss.logging.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private static final Logger LOGGER = Logger.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AppConstants.HEADER_STRING);
        if (header == null) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            LOGGER.error("Error authenticating", e);
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(req, res);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader(AppConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String username = Jwts.parser()
                    .setSigningKey(AppConstants.SECRET.getBytes("UTF-8"))
                    .parseClaimsJws(token.replace(AppConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}