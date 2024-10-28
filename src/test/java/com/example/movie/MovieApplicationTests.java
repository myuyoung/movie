package com.example.movie;

import com.example.movie.domain.Movie;
import com.example.movie.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieApplicationTests {
	@Autowired
	private MovieRepository movieRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void insert(){
		Movie movie = new Movie();
		movie.setName("파묘");
		movie.setDirector("장재현");
		movie.setDistributer("쇼박스");
		movie.setActor("김고은");
		movie.setRating(3.5);
		movie.setPrice(20000);
		movie.setPlot("미국 LA, 거액의 의뢰를 받은 무당 ‘화림’(김고은)과 ‘봉길’(이도현)은 기이한 병이 대물림되는 집안의 장손을 만난다.");
		this.movieRepository.save(movie);
	}

}
