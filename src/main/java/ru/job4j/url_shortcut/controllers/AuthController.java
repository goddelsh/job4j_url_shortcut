package ru.job4j.url_shortcut.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.models.RegistrationResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {
    @RequestMapping("/registration")
    public ResponseEntity<RegistrationResponse> reg (@RequestBody Map<String, String> req) {
        var result = new RegistrationResponse();
        if (req.get("site") != null) {
            result.setLogin("123");
            result.setPassword("123");
            result.setRegistration(true);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/auth")
    public ResponseEntity<Map<String, String>> auth (@RequestBody Map<String, String> req) {
        var result = new HashMap<String, String>();
        if (req.get("login") != null && req.get("password") != null ) {
            result.put("token", "12313123");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
