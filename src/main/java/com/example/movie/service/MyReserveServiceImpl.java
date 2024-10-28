package com.example.movie.service;

import com.example.movie.domain.Reservation;
import com.example.movie.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyReserveServiceImpl implements MyReserveService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public List<Reservation> findAllReservation(Principal principal){
        //로그인한 사용자 아이디 가져와서 cid변수에 저장
        String userName=principal.getName();
        //cid에 해당하는 정보를 DB에서 찾아서 리턴
        return reservationRepository.findByUserName(userName);
    }
    //ResponseEntity 객체를 이용하여 응답 데이터 처리
    @Override
    public ResponseEntity<Void> cancelReservation(Long reservation_id){
        //DB의 예매정보에서 예매번호에 해당하는 정보 가져오기
        Optional<Reservation> optionalReserve = reservationRepository.findById(reservation_id);
        if(optionalReserve.isPresent()){
            reservationRepository.deleteById(reservation_id);
             return ResponseEntity.noContent().build();
        }else{
             return ResponseEntity.noContent().build();
        }
    }
    @Override
    public Optional<Reservation> findReservation(Long reservation_id){
        return reservationRepository.findById(reservation_id);
    }
}
