package com.example.testsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    @GetMapping("/api/case1")
    public String case1() {
        return "/api/case1";
    }

    @GetMapping("/api/case2")
    public String case2() {
        return "/api/case2";
    }

    @GetMapping("/api/case3")
    public String case3() {
        return "/api/case3";
    }
}
