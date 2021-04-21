package ru.job4j.url_shortcut.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.models.Shortcut;
import ru.job4j.url_shortcut.models.StatisticResponse;
import ru.job4j.url_shortcut.services.InfoService;
import ru.job4j.url_shortcut.services.RegistrationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RegController {

    final private RegistrationService registrationService;
    final private InfoService infoService;

    public RegController(RegistrationService registrationService, InfoService infoService) {
        this.registrationService = registrationService;
        this.infoService = infoService;
    }


    @PostMapping("/convert")
    public ResponseEntity<Shortcut> convert(@RequestBody Map<String, String> req,
                                            @AuthenticationPrincipal String user) {
        var result = registrationService.getShortcut(req.get("url"), user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/redirect/{id}")
    public String redirect(@PathVariable String id) {
        var result = infoService.getFullUrl(id);
        return "redirect:" + (result != null ? result : "/") ;
    }

    @GetMapping("/statistic")
    public List<StatisticResponse> statistic(@AuthenticationPrincipal String user) {
        var result = infoService.getStatistic(user);
        return result;
    }
}
