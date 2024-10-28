package com.example.movie.controller;

import com.example.movie.domain.Reservation;
import com.example.movie.service.MyReserveService;
import com.example.movie.service.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/my")
public class MyReserveController {
    @Autowired
    private MyReserveService myReserveService;
    @Autowired
    private ReservationService reservationService;
    //인증된 사용자의 예매내역만 보여지게 하기
    @GetMapping("/myReserve")
    public String findAllReservations(Principal principal, Model model){
        List<Reservation> reservations = myReserveService.findAllReservation(principal);
        model.addAttribute("reservations", reservations);
        return "my/myReserve";
    }
    //예매 취소
    @DeleteMapping("/myReserve/{reservation_id}")
    public ResponseEntity<?> cancelReservation(@PathVariable("reservation_id") Long reservationId) {
        // 서비스 호출하여 예약 취소 처리
        boolean isCancelled = reservationService.cancelReservation(reservationId);
        if (isCancelled) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
