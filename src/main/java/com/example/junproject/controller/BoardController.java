package com.example.junproject.controller;

import com.example.junproject.domain.dto.BoardInsertDTO;
import com.example.junproject.domain.dto.BoardListDTO;
import com.example.junproject.repository.BoardEntityRepository;
import com.example.junproject.security.MyUserDetails;
import com.example.junproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BoardController {

    @Autowired
    BoardService service;
    @Autowired
    private BoardEntityRepository boardEntityRepository;

    @GetMapping("/progress")  //진행현황 페이지 이동
    public String list(Model model) {
        List<BoardListDTO> result = boardEntityRepository.findAll().stream().map(BoardListDTO::new).collect(Collectors.toList());
        model.addAttribute("progressList", result);
        return "progress/progress";
    }


    @GetMapping("/write") //글쓰기페이지이동
    public String write(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        service.progressList(model, myUserDetails.getEmail());

        return "progress/write";
    }

    @PostMapping("/write")
    public String boardSave(BoardInsertDTO dto, @AuthenticationPrincipal MyUserDetails userDetails) {
        long eno = userDetails.getMno();
        String writer = userDetails.getName();
        service.boardSave(dto, eno);
        return "redirect:/progress";
    }

    @GetMapping("/write/{bno}")
    public String boardView(@PathVariable long bno, Model model) {
        List<BoardListDTO> list = boardEntityRepository.findById(bno).stream().map(BoardListDTO::new).collect(Collectors.toList());
        model.addAttribute("list", list);
        return "progress/detail";
    }
}
