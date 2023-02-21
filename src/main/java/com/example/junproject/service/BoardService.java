package com.example.junproject.service;


import com.example.junproject.domain.dto.BoardInsertDTO;
import com.example.junproject.domain.dto.BoardListDTO;
import com.example.junproject.domain.dto.BoardUpdateDTO;
import com.example.junproject.domain.entity.BoardEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface BoardService {


    void boardSave(BoardInsertDTO dto, String email);

    List<BoardListDTO> progressList(Model model, String email);

    BoardEntity boardView(long bno, Model model);

    void boardUpdate(long bno, BoardUpdateDTO dto);

    void boardDelete(long bno);

}
