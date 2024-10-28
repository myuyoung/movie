package com.example.movie.repository;

import com.example.movie.user.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Customer, String> {
    //등급 설정
    Customer findByUserName(String userName);
    //@EntityGraph : 엔티티(객체)들끼리 연관되어 있는 관계가 있으면 그 관계를 그래프로 표현해줄 수 있는 객체임. JPA가 어떤 엔티티(entity)를 불러올 때 그 엔티티와 연관된 객체를 불러올 것인지에 대한 정보 제공함.
    //getWithRoles 메서드는 Customer테이블에서 멤버 아이디가 같은 데이터를 조회하는 메서드
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Customer m where m.userName = :userName")
    Optional<Customer> getWithRoles(@Param("userName") String userName);    
    
}
