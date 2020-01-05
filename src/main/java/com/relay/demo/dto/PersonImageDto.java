package com.relay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@AllArgsConstructor
public class PersonImageDto {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;
    private String image;
    public PersonImageDto() {

    }

}
