package com.relay.demo.dto;


import lombok.Data;

@Data
public class UserPassDto {
    private String login;
    private String password;
    public UserPassDto() {
    }

    public UserPassDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
