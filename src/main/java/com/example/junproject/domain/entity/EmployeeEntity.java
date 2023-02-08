package com.example.junproject.domain.entity;

import com.example.junproject.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long no;

    @Column(nullable = false)
    private String email; // 로그인 이메일

    @Column(nullable = false)
    private String name; // 사용자 이름

    @Column(nullable = false)
    private String pass; // 사용자 비밀번호

    private boolean deleted; //삭제여부 0:탈퇴


    public EmployeeEntity addRole(MyRole role ) { //등록시 addRole 사용하기 위해.
        roles.add(role);
        return this;
    }

    @CollectionTable(name = "my_role")
    @Builder.Default
    @Enumerated // 설정하지 않았음. 숫자를 기본으로
    @ElementCollection(fetch = FetchType.LAZY) //EAGER로 썼었으나  실무에서는 lazy로 많이 사용한다고 하여 lazy 타입으로 시도함.
    Set<MyRole> roles = new HashSet<>();
}
