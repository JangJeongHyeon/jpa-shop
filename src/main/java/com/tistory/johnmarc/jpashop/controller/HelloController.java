package com.tistory.johnmarc.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        log.info("Hello Controller!");
        model.addAttribute("data", "Hello!!!");
        return "greeting";
    }
}
