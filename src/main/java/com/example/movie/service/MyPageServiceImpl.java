package com.example.movie.service;

import com.example.movie.dto.CustomerJoinDTO;
import com.example.movie.dto.ReservationDTO;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyPageServiceImpl implements MyPageService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ReservationDTO> findAllReservations(){
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::fromReservation)
                .collect(Collectors.toList());
    }
    @Override
    public List<CustomerJoinDTO> findAllCustomers(){
        return userRepository.findAll().stream()
                .map(CustomerJoinDTO::fromCustomer)
                .collect(Collectors.toList());
    }

}
