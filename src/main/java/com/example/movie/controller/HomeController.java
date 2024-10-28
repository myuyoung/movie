package com.example.movie.controller;

import com.example.movie.domain.BoxOffice;
import com.example.movie.repository.BoxOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BoxOfficeRepository boxOfficeRepository;
    @GetMapping("/")
    public String home(Model model){
        List<BoxOffice> boxOfficeList = boxOfficeRepository.findAll();
        model.addAttribute("boxOfficeList", boxOfficeList);
        return "home";
    }

}
