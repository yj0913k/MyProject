package com.example.junproject.controller;

import com.example.junproject.security.properties.KakaoProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ConfigurationProperties
public class KakaoController {

    private final KakaoProperties properties;

    public KakaoController(KakaoProperties properties) {
        this.properties = properties;
    }

    //@ConfigurationProperties이게 controller 단에 달려있어야함
    @Value("${javascript}") //properties에 정의된 값 넣기
    private String javascript; //사용할 이름 만들어주기


    @GetMapping("/kakao/kakaoMapSearch")
    @ResponseBody
    public String mapSearch(Model model) {  //javascript key 감싸서 보내는 연습
        model.addAttribute("javascriptKey", javascript);
        return "mapApi/kakaoMapForSearch";
    }
}
