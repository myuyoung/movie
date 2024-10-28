package com.example.movie.service;

import com.example.movie.repository.UserRepository;
import com.example.movie.user.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    //주로 어떤 객체를 Bean 형식으로 주입할 때 사용하는 어노테이션
    //생성자가 1개일 때는 생략 가능함.
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer create(String userName, String password, String email){

        Customer user = new Customer();
        user.setUserName(userName);
        //비밀번호 암호화해서 DB의 user테이블에 저장
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        this.userRepository.save(user);
        return user;
    }
}
