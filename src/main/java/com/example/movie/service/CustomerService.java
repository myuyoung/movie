package com.example.movie.service;

import com.example.movie.dto.CustomerJoinDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    //회원이 이미 존재하면 예외처리함
    static class cidException extends Exception{
    }
    //void join(CustomerJoinDTO customerJoinDTO) throws cidException;
    //UserDetails loadUserByUserName(String cid);
}
