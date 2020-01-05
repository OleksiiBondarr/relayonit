package com.relay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Data
@AllArgsConstructor
public class PersonIdDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String businessNumber;
    private String email;
    public PersonIdDto() {

    }

}
