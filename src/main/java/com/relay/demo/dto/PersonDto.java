package com.relay.demo.dto;

        import lombok.Data;

@Data
public class PersonDto {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;

    public PersonDto() {

    }

    public PersonDto(int id, String name, String surname, String phoneNumber, String businessNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.email = email;
    }
}
