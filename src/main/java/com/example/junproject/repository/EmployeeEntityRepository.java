package com.example.junproject.repository;

import com.example.junproject.domain.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmailAndDeleted(String username, boolean b);

}
