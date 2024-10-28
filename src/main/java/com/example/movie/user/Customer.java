package com.example.movie.user;

import com.example.movie.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Customer {
    @Id
    private String userName;    //회원아이디
    private String password;     //회원비밀번호
    private String email;    //회원이메일
    private String grade;    //회원등급
    private boolean del;   //권한 삭제

    //각 타입 컬렉션을 매핑할 때 사용, 해당 필드가 컬렉션 객체임을 JPA에게 알려줌
    @ElementCollection(fetch= FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();
    public void changePassword(String password){
        this.password=password;
    }
    //회원(멤버)에게 권한 설정
    public void addRole(UserRole userRole){
        this.roleSet.add(userRole);
    }
    //권한 삭제
    public void clearRole(){
        this.roleSet.clear();
    }
}
