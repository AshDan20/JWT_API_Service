package com.poc.jwtapi.JWTApiService;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class HomeController {
    @GetMapping("/")
    public String serveHome() {
        System.out.println(":: APP Running .... ::");
        return "Application is running";
    }
}