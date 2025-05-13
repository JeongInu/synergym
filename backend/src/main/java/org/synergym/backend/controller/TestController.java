package org.synergym.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apiTest")
public class TestController {

    @GetMapping("/test")
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        System.out.println("@@@@TEST@@@@");
        response.put("message", "good ;)");
        return response;
    }

}
