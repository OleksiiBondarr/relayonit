package com.relay.demo.services;

import com.relay.demo.dto.PersonDto;
import com.relay.demo.entities.Person;
import com.relay.demo.exceptions.WrongDataException;
import com.relay.demo.repositories.PersonRepository;
import com.relay.demo.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonServiceImp(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonDto> getPeople() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public PersonDto getPerson(int id) {
        if (repository.findById(id).isPresent())
            return convertToDto(repository.findById(id).get());
        throw new PersonNotFoundException(id);
    }

    @Override
    public PersonDto postPerson(PersonDto personDto) {
        if (verifyPerson(personDto)) {
            repository.save(convertFromDto(personDto));
            return personDto;
        }
        throw new WrongDataException();
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        if (verifyPerson(personDto)) {
            Person person = convertFromDto(getPerson(personDto.getId()));
            person.setName(personDto.getName());
            person.setSurname(personDto.getSurname());
            person.setPhoneNumber(personDto.getPhoneNumber());
            person.setBusinessNumber(personDto.getBusinessNumber());
            person.setEmail(personDto.getEmail());
            return personDto;
        }
        throw new WrongDataException();
    }

    @Override
    public void deleteUser(int id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new PersonNotFoundException(id);
    }

    private PersonDto convertToDto(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getSurname(), person.getPhoneNumber(), person.getBusinessNumber(), person.getEmail());
    }

    private Person convertFromDto(PersonDto personDto) {
        if (repository.findById(personDto.getId()).isPresent())
            return repository.findById(personDto.getId()).get();
        else
            throw new PersonNotFoundException(personDto.getId());
    }

    private boolean verifyPerson(PersonDto personDto) {
        Pattern name = Pattern.compile("[A-Z][a-z]*");
        Matcher nameMatcher = name.matcher(personDto.getName());
        Matcher surnameMatcher = name.matcher(personDto.getSurname());
        Pattern phone = Pattern.compile("[0-9]{4,}");
        Matcher phoneMatcher = phone.matcher(personDto.getPhoneNumber());
        Matcher businessMatcher = phone.matcher(personDto.getBusinessNumber());
        Pattern email = Pattern.compile("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}" +
                "(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
        Matcher emailMatcher = email.matcher(personDto.getEmail());
        return nameMatcher.matches() && surnameMatcher.matches() && phoneMatcher.matches() && businessMatcher.matches() && emailMatcher.matches();
    }
}
