package kr.co.wikibook.gallery_jwt_jpa_jwt_jpa.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @GetMapping("/greeting")
    public String greet() {
        return "Hello Spring";
    }
}