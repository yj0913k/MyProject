package com.example.junproject.service;


import com.example.junproject.domain.dto.BoardInsertDTO;
import com.example.junproject.domain.dto.BoardListDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface BoardService {


    void boardSave(BoardInsertDTO dto, long writer);


    List<BoardListDTO> progressList(Model model, String email);
}
