package com.example.junproject.security;

import com.example.junproject.domain.entity.EmployeeEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class MyUserDetails extends User {
    private String email;
    private String name;
    private long mno;
    private String pass;

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }


    //user에서 인증된 정보들을 가지고 오는 과정
    public MyUserDetails(EmployeeEntity entity) {
        this(entity.getEmail(), entity.getPass(),entity.getRoles()
                .stream()
                .map(myRole -> new SimpleGrantedAuthority(myRole.getRole()))
                .collect(Collectors.toList()));

        this.email = entity.getEmail();
        this.pass = entity.getPass();
        this.mno = entity.getNo();
    }
}
