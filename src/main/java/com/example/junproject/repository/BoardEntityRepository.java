package com.example.junproject.repository;

import com.example.junproject.domain.dto.BoardListDTO;
import com.example.junproject.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {

    //프로그램진행현황 리스트 보여주기.
}
