package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/best")
    public String best() {
        return "/best";
    }

    @GetMapping("/newPage")
    public String newpage() {
        return "/newPage";
    }

    @GetMapping("/outer")
    public String outer() {
        return "/outer";
    }

    @GetMapping("/top")
    public String top() {
        return "/top";
    }

    @GetMapping("/pants")
    public String pants() {
        return "/pants";
    }

    @GetMapping("/shoes")
    public String shoes() {
        return "/shoes";
    }

    @GetMapping("/acc")
    public String acc() {
        return "/acc";
    }
}
