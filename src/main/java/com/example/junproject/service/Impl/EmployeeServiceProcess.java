package com.example.junproject.service.Impl;

import com.example.junproject.domain.dto.EmployeeInsertDTO;
import com.example.junproject.domain.entity.EmployeeEntity;
import com.example.junproject.repository.EmployeeEntityRepository;
import com.example.junproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceProcess implements EmployeeService {
    @Autowired
    private final EmployeeEntityRepository employeeEntityRepository;

    @Autowired
    private PasswordEncoder pe;


    public EmployeeServiceProcess(EmployeeEntityRepository employeeEntityRepository) {
        this.employeeEntityRepository = employeeEntityRepository;
    }

    @Override
    public void save(EmployeeInsertDTO dto) {
        EmployeeEntity entity = employeeEntityRepository.save(dto.toEntity(pe));

    }
}
