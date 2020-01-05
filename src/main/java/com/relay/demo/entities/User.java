package com.relay.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Data
public class User {
    @Id
    private String login;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
