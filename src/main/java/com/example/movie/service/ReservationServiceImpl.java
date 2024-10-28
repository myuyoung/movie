package com.example.movie.service;

import com.example.movie.domain.Reservation;
import com.example.movie.dto.ReservationDTO;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.UserRepository;
import com.example.movie.user.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    @Override
    public void processReservation(ReservationDTO reservationDTO){
        log.info("예약 정보: {}", reservationDTO);
    }
    //예매 취소
    @Override
    public boolean cancelReservation(Long reservation_id){
        try {
            reservationRepository.deleteById(reservation_id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public List<ReservationDTO> findAllReservations(){
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::forOccupiedSeat)
                .collect(Collectors.toList());
    }
    @Override
    public String getUserGrade(String userName){
        int reservationCount = countReservationByUserName(userName);
        String grade;
        //예매내역이 5개 이상이면 고객 등급은 Gold, 5개 미만이면 Silver
        if(reservationCount >=5 ){
            grade = "Gold";
        }else{
            grade = "Silver";
        }
        return grade;
    }
    @Override
    public int countReservationByUserName(String userName){
        //예매 내역 테이블에서 아이디가 같은 데이터의 개수를 구해서 리턴
        return reservationRepository.countByUserName(userName);
    }
    @Override
    public void updateCustomerGrade(String userName){
        Customer customer = userRepository.findByUserName(userName);
        int reservationCount = countReservationByUserName(userName);
        String grade;
        if(reservationCount >= 5){
            grade = "Gold";
        }else{
            grade = "Silver";
        }
        customer.setGrade(grade);
        userRepository.save(customer);
    }
}
