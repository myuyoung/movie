package com.example.movie.service;

import com.example.movie.dto.CustomerJoinDTO;
import com.example.movie.dto.ReservationDTO;

import java.util.List;

public interface MyPageService {
    List<ReservationDTO> findAllReservations();
    List<CustomerJoinDTO> findAllCustomers();
}
