package com.example.movie.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//회원가입 폼 유효성 검사
@Getter
@Setter
public class UserForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 Id를 입력해 주세요.")
    private String userName;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotEmpty(message = "비밀번호 확인을 입력해 주세요.")
    private String password2;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email
    private String email;
}
