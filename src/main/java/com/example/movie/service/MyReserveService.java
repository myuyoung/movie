package com.example.movie.service;

import com.example.movie.domain.Reservation;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface MyReserveService {
    //로그인한 사용자의 예매 정보 모두 검색
    List<Reservation> findAllReservation(Principal principal);
    //예매 취소
    ResponseEntity<Void> cancelReservation(Long reservation_id);
    //1개의 예매 정보 검색
    Optional<Reservation> findReservation(Long reservation_id);

}
