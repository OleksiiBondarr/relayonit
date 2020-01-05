package com.relay.demo.services;

import com.relay.demo.dto.PersonDto;
import com.relay.demo.dto.PersonIdDto;
import com.relay.demo.dto.PersonImageDto;
import com.relay.demo.entities.Person;
import com.relay.demo.entities.User;
import com.relay.demo.exceptions.WrongDataException;
import com.relay.demo.repositories.PersonRepository;
import com.relay.demo.exceptions.PersonNotFoundException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<PersonImageDto> getPeople() {
        return repository.findAll().stream().map(this::convertToImageDto).collect(Collectors.toList());
    }


    @Override
    public PersonImageDto getPerson(int id) {
        if (repository.findById(id).isPresent())
            return convertToImageDto(repository.findById(id).get());
        throw new PersonNotFoundException(id);
    }

    @Override
    public void postPerson(PersonIdDto personIdDto, MultipartFile multipartFile) {
        if (verifyPerson(personIdDto.getName(), personIdDto.getSurname(), personIdDto.getPhoneNumber(), personIdDto.getBusinessNumber(), personIdDto.getEmail())) {
            String convertedImage = convertImage(multipartFile);
            repository.save(new Person(personIdDto.getName(), personIdDto.getSurname(), personIdDto.getPhoneNumber(), personIdDto.getBusinessNumber(), personIdDto.getEmail(), convertedImage));
        } else {
            throw new WrongDataException();
        }
    }

    @Override
    public void updatePerson(PersonDto personDto, MultipartFile multipartFile) {
        if (verifyPerson(personDto.getName(), personDto.getSurname(), personDto.getPhoneNumber(), personDto.getBusinessNumber(), personDto.getEmail())) {
            Person person = convertFromDto(personDto.getId());
            person.setName(personDto.getName());
            person.setSurname(personDto.getSurname());
            person.setPhoneNumber(personDto.getPhoneNumber());
            person.setBusinessNumber(personDto.getBusinessNumber());
            person.setEmail(personDto.getEmail());
            String convertedImage = convertImage(multipartFile);
            if (!(convertedImage == null))
                person.setImage(convertedImage);
            repository.save(person);
        } else {
            throw new WrongDataException();
        }
    }

    @Override
    public void deleteUser(int id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new PersonNotFoundException(id);
    }

    private PersonImageDto convertToImageDto(Person person) {
        return new PersonImageDto(person.getId(), person.getName(), person.getSurname(), person.getPhoneNumber(), person.getBusinessNumber(), person.getEmail(), person.getImage());
    }

    private Person convertFromDto(int id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        else
            throw new PersonNotFoundException(id);
    }

    private boolean verifyPerson(String name, String surname, String phoneNumber, String businessNumber, String email) {
        Pattern namePattern = Pattern.compile("[A-Z][a-z]*");
        Matcher nameMatcher = namePattern.matcher(name);
        Matcher surnameMatcher = namePattern.matcher(surname);
        Pattern phonePattern = Pattern.compile("[0-9]{4,}");
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        Matcher businessMatcher = phonePattern.matcher(businessNumber);
        Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}" +
                "(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
        Matcher emailMatcher = emailPattern.matcher(email);
        return nameMatcher.matches() && surnameMatcher.matches() && phoneMatcher.matches() && businessMatcher.matches() && emailMatcher.matches();
    }

    private String convertImage(MultipartFile multipartFile) {
        if (!(multipartFile.isEmpty())) {
            try {
                return Base64.encodeBase64String(multipartFile.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
