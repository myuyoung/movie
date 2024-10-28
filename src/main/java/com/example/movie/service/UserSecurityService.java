package com.example.movie.service;

import com.example.movie.repository.UserRepository;
import com.example.movie.user.Customer;
import com.example.movie.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> _user = Optional.ofNullable(this.userRepository.findByUserName(username));
        if(_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Customer customer = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        //만약 사용자ID(username)가 admin이라면
        if("admin".equals(username)){
            //authorities배열에 ADMIN관리자 값(ROLE_ADMIN) 추가
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        //사용자ID(username)가 admin이 아니라면
        }else{
            //authorities배열에 USER값(ROLE_USER) 추가
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(customer.getUserName(), customer.getPassword(), authorities);
    }
}
