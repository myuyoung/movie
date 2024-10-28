package com.example.movie.security;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//환경설정하는 파일에 붙이는 어노테이션
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //비밀번호를 암호화하거나 복호화해주는 객체 생성
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean이란 스프링 컨테이너가 관리하는 재사용 가능한 컴포넌트 객체
    //인스턴스화된 자바 객체, 클래스의 멤버변수, Getter/Setter메서드 포함.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF : 크로스 사이트 요청 위조
        //X-Frame-Options헤더 : clickjacking attack 방지를 위한 헤더임
        //clickjacking attack : 웹사이트 사용자를 속여 자신도 모르게 악성 링크를 클릭하도록 하는 인터페이스 기반 공격
        http.authorizeHttpRequests((authrizeHttpRequests) -> authrizeHttpRequests.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/", "/user/**").permitAll().anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headers)->headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                //history.back()했을 때 양식 다시 제출 확인 안뜨게 하기
                //.headers(headers -> headers.cacheControl(cache -> cache.disable()))
                .formLogin((formLogin)->formLogin.loginPage("/user/login")
                .defaultSuccessUrl("/"))
                .logout((logout)->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        return http.build();
    }
    //스프링 시큐리티의 인증을 처리하는 객체
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
