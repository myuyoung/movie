package com.example.movie.dto;

import com.example.movie.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long reservation_id;    //번호
    private String reservation_day;   //예매날짜
    private String reservation_num;   //예매번호

    private String cid;
    private Long rank;
    private String movieNm;
    private String image;

    //마이 페이지, 마이 예약 페이지로 가져가는 데이터
    public static ReservationDTO fromReservation(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservation_id(reservation.getReservation_id());
        reservationDTO.setReservation_day(reservation.getReservation_day());
        reservationDTO.setReservation_num(reservation.getReservation_num());
        reservationDTO.setImage(reservation.getImage());
        reservationDTO.setMovieNm(reservation.getMovieNm());
        return reservationDTO;
    }
    //reserve/start.html 페이지에 reservation_num값 넘기기
    public static ReservationDTO forOccupiedSeat(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservation_num(reservation.getReservation_num());
        return reservationDTO;
    }
}
