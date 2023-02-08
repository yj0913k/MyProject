package com.example.junproject.security;

import com.example.junproject.domain.entity.EmployeeEntity;
import com.example.junproject.repository.EmployeeEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

@Log4j2
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(employeeEntityRepository.findByEmailAndDeleted(username,false).orElseThrow(
                ()->new UsernameNotFoundException("존재하지않는 이메일입니다.")));//noSuchElementException
    }
}
