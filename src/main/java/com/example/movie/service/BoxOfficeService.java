package com.example.movie.service;

import com.example.movie.domain.BoxOffice;
import com.example.movie.repository.BoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoxOfficeService {
    @Autowired
    private final BoxOfficeRepository boxOfficeRepository;
    public BoxOffice save(BoxOffice boxOffice){
        return boxOfficeRepository.save(boxOffice);
    }
    //Optional 객체의 orElse()메서드 : if ~ else문을 처리하는 메서드
    public BoxOffice findById(Long rank){
        Optional<BoxOffice> optionalBoxOffice = boxOfficeRepository.findById(rank);
        return optionalBoxOffice.orElse(null);
    }
}
