package com.example.baked.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping(value = "/test")
    @ResponseBody
    public String getSomething() {
        return "student/login.html";
    }

    @PostMapping(value = "/post")
    @ResponseBody
    public String postSomething(@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "age", required = false) String age) {
        String response = "ID: " + id;
        return response;
    }

    @PostMapping(value = "/post2")
    @ResponseBody
    public String postSomething2(@RequestBody ResquestBodyTest req) {
        String response = "ID: " + req.id + " - Age: " + req.age;
        return response;
    }
}
