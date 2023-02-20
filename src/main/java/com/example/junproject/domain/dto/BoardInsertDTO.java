package com.example.junproject.domain.dto;

import com.example.junproject.domain.entity.BoardEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardInsertDTO {

    private int bno;
    private String content;
    private String title;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private long writerNo;


    private long count;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .content(content)
                .title(title)
                .build();
    }

}
