package com.relay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;

    public PersonDto() {

    }

}
