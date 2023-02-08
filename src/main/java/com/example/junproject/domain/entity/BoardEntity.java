package com.example.junproject.domain.entity;

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
    @Lob
    private String content;


    @Column(nullable = true)
    private long count;



    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDateTime;


    @PrePersist
    public void createdAt() {
        this.createdDateTime = LocalDateTime.now();
    }


    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeEntity writer;


    public BoardEntity setWriter(EmployeeEntity writer) {
        this.writer = writer;
        return this;
    }
}
