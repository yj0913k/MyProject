package com.example.junproject.domain.dto;

import com.example.junproject.domain.entity.EmployeeEntity;
import com.example.junproject.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Data
public class EmployeeInsertDTO {

    private long no;
    private String email; // 로그인 이메일
    private String name; // 사용자 이름
    private String pass; // 사용자 비밀번호
    private boolean deleted; //삭제여부 0:탈퇴
    private String role;

    public EmployeeEntity toEntity(PasswordEncoder pe) {
        return EmployeeEntity.builder()
                .no(no)
                .email(email)
                .name(name)
                .pass(pe.encode(pass))
                .build()
                .addRole(MyRole.valueOf(role));
    }

}
