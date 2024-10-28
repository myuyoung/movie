package com.example.movie.dto;

import com.example.movie.user.Customer;
import lombok.Data;

@Data
public class CustomerJoinDTO {
    private String cid;
    private String cpw;
    private String email;
    private String grade;
    private boolean del;
    private boolean social;


    public static CustomerJoinDTO fromCustomer(Customer customer) {
        CustomerJoinDTO customerJoinDTO = new CustomerJoinDTO();
        customerJoinDTO.setCid(customerJoinDTO.getCid());
        customerJoinDTO.setGrade(customerJoinDTO.getGrade());
        return customerJoinDTO;
    }
}
