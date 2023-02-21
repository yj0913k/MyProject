package com.example.junproject.domain.entity;

import com.example.junproject.domain.dto.BoardUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity { //사이드바 프로젝트 진행 현황

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Lob
    private String content;


    @Column(nullable = true)
    private long count;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDateTime;


    @PrePersist
    public void createdAt() {
        this.createdDateTime = LocalDateTime.now();
    }


    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeEntity employeeEntity;


    public BoardEntity setWriter(EmployeeEntity employeeEntity) {
        this.name = employeeEntity.getName();
        this.email = employeeEntity.getEmail();
        return this;
    }

    //편의 메서드 (수정)
    public BoardEntity update(BoardUpdateDTO dto) {
        this.title=dto.getTitle();
        this.content=dto.getContent();
        return this;
    }
}
