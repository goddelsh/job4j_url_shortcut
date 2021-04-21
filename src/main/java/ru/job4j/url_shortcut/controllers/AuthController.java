package ru.job4j.url_shortcut.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.models.Account;
import ru.job4j.url_shortcut.models.RegistrationResponse;
import ru.job4j.url_shortcut.services.AuthService;
import ru.job4j.url_shortcut.services.RegistrationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    final private AuthService authService;
    final private RegistrationService registrationService;


    public AuthController(AuthService authService, RegistrationService registrationService) {
        this.authService = authService;
        this.registrationService = registrationService;
    }

    @RequestMapping("/registration")
    public ResponseEntity<RegistrationResponse> reg (@RequestBody Map<String, String> req) {
        var result = new RegistrationResponse();
        if (req.get("site") != null) {
            var account = registrationService.regSite(req.get("site"));
            result.setRegistration(true);
            result.setLogin(account.getLogin());
            result.setPassword(account.getPassword());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/auth")
    public ResponseEntity<Map<String, String>> auth (@RequestBody Map<String, String> req) throws Exception {
        var result = new HashMap<String, String>();
        if (req.get("login") != null && req.get("password") != null ) {
            result.put("token", authService.getToken(req.get("login"), req.get("password")));
        }
        return ResponseEntity.ok(result);
    }
}
