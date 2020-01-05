package com.relay.demo.services;

import com.relay.demo.dto.PersonDto;
import com.relay.demo.dto.PersonIdDto;
import com.relay.demo.dto.PersonImageDto;
import com.relay.demo.entities.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonService {

    List<PersonImageDto> getPeople();

    PersonImageDto getPerson(int id);

    void postPerson(PersonIdDto personDto, MultipartFile multipartFile);

    void updatePerson(PersonDto personDto, MultipartFile multipartFile);

    void deleteUser(int id);
}
