package com.example.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxOffice {
    @Id
    @Column(name= "`rank`")
    private Long rank;
    private Integer id;
    @Column(length = 255, nullable = false)
    private String movieNm;    //영화명
    @Column(nullable = true)
    private String image;
    @Column(length=1000, nullable = true)
    private String overview;
    @Column(nullable = true)
    private Integer runtime;
    @Column(nullable = true)
    private String releaseDate;
}
