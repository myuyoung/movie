package com.example.movie.service;

import com.example.movie.domain.Movie;
import com.example.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public void create(String name, String director, String distributer, String actor, double rating, double price, String plot){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDirector(director);
        movie.setDistributer(distributer);
        movie.setActor(actor);
        movie.setRating(rating);
        movie.setPrice(price);
        movie.setPlot(plot);
        this.movieRepository.save(movie);
    }
    public Movie getMovie(Long id){
        Optional<Movie> movie = this.movieRepository.findById(id);
        //if(movie.isPresent()){
            return movie.get();
        //}
    }
}
