package com.example.movie.controller;

import com.example.movie.service.UserService;
import com.example.movie.user.UserForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @GetMapping("/join")
    public String join(UserForm userForm){
        return "user/join";
    }
    @PostMapping("/join")
    public String join(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/join";
        }
        if(!userForm.getPassword().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "**비밀번호가 일치하지 않습니다.**");
            return "user/join";
        }
        //중복 회원 가입 방지
        //DataIntegrityViolationException : SQL문에서 에러가 있거나 Data가 잘못되었을 경우 예외처리하는 객체
        try {
            userService.create(userForm.getUserName(), userForm.getPassword(), userForm.getEmail());
        } catch(DataIntegrityViolationException e) {
            bindingResult.rejectValue("userName", "Duplicate.userName", "이미 등록된 사용자입니다.");
            return "user/join";
        } catch(Exception e) {
            bindingResult.rejectValue("userName", "Exception", e.getMessage());
            return "user/join";
        }

        //userService.create(userForm.getUsername(), userForm.getPassword(), userForm.getEmail());
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
}
