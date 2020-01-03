package com.relay.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;

    public Person() {

    }

    public Person(String name, String surname, String phoneNumber, String businessNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.email = email;
    }
}
