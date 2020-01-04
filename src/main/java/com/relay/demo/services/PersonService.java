package com.relay.demo.services;

import com.relay.demo.dto.PersonDto;
import com.relay.demo.dto.PersonIdDto;
import com.relay.demo.entities.Person;

import java.util.List;

public interface PersonService {

    List<PersonDto> getPeople();

    PersonDto getPerson(int id);

    void postPerson(PersonIdDto personDto);

    void updatePerson(PersonDto personDto);

    void deleteUser(int id);
}
