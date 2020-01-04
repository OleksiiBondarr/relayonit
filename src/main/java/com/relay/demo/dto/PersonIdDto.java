package com.relay.demo.dto;

        import lombok.Data;

@Data
public class PersonIdDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;

    public PersonIdDto() {

    }

    public PersonIdDto( String name, String surname, String phoneNumber, String businessNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.email = email;
    }
}
