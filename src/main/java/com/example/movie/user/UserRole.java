package com.example.movie.user;

import lombok.Getter;
//enum 클래스는 상수와 메서드를 정의할 수 있는 객체
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value){
        this.value=value;
    }
    private String value;
}
