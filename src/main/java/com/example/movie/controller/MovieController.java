package com.example.movie.controller;

import com.example.movie.domain.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    //Movie 테이블에서 영화 전체 목록 읽어오기
    @Autowired
    private MovieRepository movieRepository;
    @GetMapping("/list")
    public String list(Model model){
        List<Movie> movieList = this.movieRepository.findAll();
        model.addAttribute("movieList", movieList);
        return "movies/list";
    }
    //Movie 테이블에 영화 정보 입력하기
    @GetMapping("/create")
    public String create(){
        return "movies/create";
    }
    @PostMapping("/create")
    public String create(@RequestParam(value="name") String name,
                         @RequestParam(value="director") String director,
                         @RequestParam(value="distributer") String distributer,
                         @RequestParam(value="actor") String actor,
                         @RequestParam(value="rating") double rating,
                         @RequestParam(value="price") double price,
                         @RequestParam(value="plot") String plot){
        this.movieService.create(name, director, distributer, actor, rating, price, plot);
        return "redirect:/movies/list";
    }
    //영화 상세 정보 보기
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Movie movie = this.movieService.getMovie(id);
        model.addAttribute("movie", movie);
        return "/movies/detail";
    }
    //예매하기
    @GetMapping("/reserve")
    public String reserve(){
        return "/movies/reserve";
    }
    @PostMapping("/reserve")
    public String reserve(Model model){
        return "/movies/reserve";
    }
}
