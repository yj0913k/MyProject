package com.example.junproject.controller;

import com.example.junproject.domain.dto.BoardInsertDTO;
import com.example.junproject.domain.dto.BoardListDTO;
import com.example.junproject.domain.dto.BoardUpdateDTO;
import com.example.junproject.repository.BoardEntityRepository;
import com.example.junproject.security.MyUserDetails;
import com.example.junproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
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
        String email = userDetails.getEmail();
        String writer = userDetails.getName();
        service.boardSave(dto, email);
        return "redirect:/progress";
    }

    @GetMapping("/write/{bno}")
    public String boardView(@PathVariable long bno, Model model) {
        List<BoardListDTO> list = boardEntityRepository.findById(bno).stream().map(BoardListDTO::new).collect(Collectors.toList());
        model.addAttribute("list", list);
        return "progress/detail";
    }

    @GetMapping("/write/edit/{bno}")
    public String boardEditing(@PathVariable long bno, Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        long mno = userDetails.getMno();
        List<BoardListDTO> list = boardEntityRepository.findById(bno).stream().map(BoardListDTO::new).collect(Collectors.toList());
        model.addAttribute("list", list);
        return "progress/edit";
    }

    @PutMapping("/write/editing/{bno}")
    public String boardEdit(@PathVariable long bno, BoardUpdateDTO dto) {
        service.boardUpdate(bno, dto);
        return "redirect:/write/{bno}";
    }

    @DeleteMapping("/write/delete/{bno}")
    public String boardDelete(@PathVariable long bno) {
        service.boardDelete(bno);
        return "redirect:/progress";
    }
}
