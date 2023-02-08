package com.example.junproject.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {

    USER("ROLE_USER", "사용자"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String role;
    private final String name;
}
