package com.example.movie.controller;

import com.example.movie.domain.BoxOffice;
import com.example.movie.repository.BoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DetailPageController {
    private final BoxOfficeRepository boxOfficeRepository;
    @GetMapping("/post/detail_api/{rank}")
    public String detailPage(@PathVariable("rank") Long rank, Model model){
        Optional<BoxOffice> optionaBoxOffice = boxOfficeRepository.findByRank(rank);
        if(optionaBoxOffice.isPresent()){
            model.addAttribute("movieData", optionaBoxOffice.get());
            return "post/detail_api";
        }else {
            return "";
        }
    }
}
