package com.example.movie.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;     //일련번호
    @Column(nullable = false)
    private String reservation_day;  //예매날짜
    @Column(nullable = false)
    private String reservation_num;  //예매번호
    @Column(name="cid")
    private String userName;         //사용자명
    @Column(name="`rank`")
    private Long rank;               //순위
    private String movieNm;          //영화명
    private String image;            //영화이미지
}
