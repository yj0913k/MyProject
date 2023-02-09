package com.example.junproject.controller;

import com.example.junproject.domain.dto.EmployeeInsertDTO;
import com.example.junproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping("/signUp")
    public String signUp(EmployeeInsertDTO dto) {
        service.save(dto);
        return "redirect:/";
    }

    @GetMapping("/user/signUp")
    public String singUp() {
        return "sign/signUp";
    }

    @GetMapping("/user/signIn")
    public String login() {
        return "sign/signIn";
    }

}
