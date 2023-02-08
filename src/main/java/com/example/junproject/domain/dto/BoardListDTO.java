package com.example.junproject.domain.dto;

import com.example.junproject.domain.entity.BoardEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListDTO {
    private long bno;
    private String title;
    private LocalDateTime createdDate;
    private long count;
    private String writerName;

    public BoardListDTO(BoardEntity entity) {
        this.bno = entity.getNo();
        this.writerName = entity.getWriter().getName();
        this.title = entity.getTitle();
        this.createdDate = entity.getCreatedDateTime();
        this.count = entity.getCount();

    }


}
