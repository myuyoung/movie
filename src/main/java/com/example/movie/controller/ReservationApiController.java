package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.movie.repository.ReservationRepository;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationApiController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservedSeats")
    public List<String> getReservedSeats(@RequestParam String movieNm) {
        return reservationRepository.findReservationSeats(movieNm);
    }
}
