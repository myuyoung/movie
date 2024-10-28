package com.example.movie.repository;

import com.example.movie.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //customer 테이블의 영화명과 reservation테이블의 영화명이 같은 영화예매번호 검색
    @Query(value = "select reservation_num from reservation where movie_nm = :box_office", nativeQuery = true)
    List<String> findReservationSeats(@Param("box_office") String movie);

    //인증된 사용자의 예매내역만 불러오는 메서드
    List<Reservation> findByUserName(String userName);

    //회원 등급 속성 추가시 필요한 메서드
    int countByUserName(String userName);
}
