package com.relay.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPassDto {
    private String login;
    private String password;

    public UserPassDto() {
    }

}
