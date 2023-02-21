package com.example.junproject.service.Impl;

import com.example.junproject.domain.dto.BoardInsertDTO;
import com.example.junproject.domain.dto.BoardListDTO;
import com.example.junproject.domain.dto.BoardUpdateDTO;
import com.example.junproject.domain.entity.BoardEntity;
import com.example.junproject.repository.BoardEntityRepository;
import com.example.junproject.repository.EmployeeEntityRepository;
import com.example.junproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceProcess implements BoardService {

    @Autowired
    BoardService service;

    @Autowired
    BoardEntityRepository boardEntityRepository;


    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    @Override
    public void boardSave(BoardInsertDTO dto, String email) {

        boardEntityRepository.save(dto.toEntity().setWriter(employeeEntityRepository.findByEmail(email).get()));

    }



    @Transactional
    @Override
    public List<BoardListDTO> progressList(Model model, String email) {
        model.addAttribute("progressList", boardEntityRepository
                .findAll()
                .stream()
                .map(BoardListDTO::new)
                .collect(Collectors.toList()));
        return null;
    }

    @Override
    public BoardEntity boardView(long bno, Model model) {
        return boardEntityRepository.findById(bno).get();


    }

    @Transactional
    @Override
    public void boardUpdate(long bno, BoardUpdateDTO dto) {
        boardEntityRepository.findById(bno).map(entity -> entity.update(dto));
    }

    @Override
    public void boardDelete(long bno) {
        boardEntityRepository.deleteById(bno);
    }


}
