package com.example.junproject.domain.dto;

import com.example.junproject.domain.entity.BoardEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListDTO {
    private long bno;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private long count;
    private String writerName;
    private String email;

    public BoardListDTO(BoardEntity entity) {
        this.bno = entity.getNo();
        this.writerName = entity.getName();
        this.content = entity.getContent();
        this.title = entity.getTitle();
        this.createdDate = entity.getCreatedDateTime();
        this.count = entity.getCount();
        this.email = entity.getEmail();

    }


}
