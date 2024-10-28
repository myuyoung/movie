package com.example.movie.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     //영화명
    private String director;      //감독
    private String distributer;  //배급사
    private String actor;    //배우
    private double rating;   //평점
    private double price;    //가격
    private String plot;     //줄거리
}
