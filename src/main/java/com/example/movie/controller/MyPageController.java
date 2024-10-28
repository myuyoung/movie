package com.example.movie.controller;

import com.example.movie.service.MyPageService;
import com.example.movie.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/my")
public class MyPageController {
    @Autowired
    private MyPageService myPageService;
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/myPage")
    public String myPage(Model model, Principal principal){
        if(principal != null) {
            String userName=principal.getName();
            //사용자 등급 업데이트
            reservationService.updateCustomerGrade(userName);
            //사용자 등급 가져오기
            String userGrade=reservationService.getUserGrade(userName);
            model.addAttribute("userGrade", userGrade);
            return "my/myPage";
        }else {
            return "redirect:/user/login";
        }
    }
}
