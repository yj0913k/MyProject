package com.example.junproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/kakao/kakaoMap")
    public String kakaoMap() {
        return "mapApi/kakaoMap";
    }
}
