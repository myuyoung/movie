package com.example.movie.service;

import com.example.movie.domain.Reservation;
import com.example.movie.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    //원래 interface객체에서는 method를 선언만 하고 {}의 내용은 작성하지 않는 것이 기본 문법인데, interface객체에서도 {}내용을 작성해서 사용하려는 메서드는 default키워드로 선언한다.
    //interface객체에서 default method를 사용하면 좋은 점 :
    //하위 호환성은 유지되고 인터페이스의 보완이 가능.
    default Reservation dtoToEntity(ReservationDTO reservationDTO){
        Reservation reservation = Reservation.builder()
                .reservation_id(reservationDTO.getReservation_id())
                .reservation_day(reservationDTO.getReservation_day())
                .reservation_num(reservationDTO.getReservation_num())
                .build();
        return reservation;
    }
    default ReservationDTO entityToDto(Reservation reservation){
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .reservation_id(reservation.getReservation_id())
                .reservation_day(reservation.getReservation_day())
                .reservation_num(reservation.getReservation_num())
                .build();
        return reservationDTO;
    }

    //예매처리
    void processReservation(ReservationDTO reservationDTO);
    //예매내역 저장
    Reservation save(Reservation reservation);
    //예매 취소
    boolean cancelReservation(Long reservation_id);
    //모든 예매내역 확인
    List<ReservationDTO> findAllReservations();
    //등급 속성 추가
    String getUserGrade(String userName);
    void updateCustomerGrade(String userName);
    int countReservationByUserName(String userName);
}
