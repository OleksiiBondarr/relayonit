package com.relay.demo.entities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    @Lob
    private String image = "";
    public Person() {

    }
    public Person(String name, String surname, String phoneNumber, String businessNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.email = email;

    }
    public Person(String name, String surname, String phoneNumber, String businessNumber, String email, String image) {
        this(name, surname, phoneNumber, businessNumber, email);
        this.image = image;
    }
}
