package com.relay.demo.services;

import com.relay.demo.dto.PersonImageDto;
import com.relay.demo.entities.Person;
import com.relay.demo.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class PersonServiceImpTest {
    @MockBean(name = "userRepository")
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImp personService;
    private Person person1;
    private Person person2;
    private Method convertToImageDto;
    private Method verifyPerson;

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        person1 = new Person("Oleksii", "Bondar", "222222", "222222", "ww@ww.ww");
        person2 = new Person("Dmytro", "Zadohin", "333333", "333333", "aa@aa.aa");
        convertToImageDto = PersonServiceImp.class.getDeclaredMethod("convertToImageDto", Person.class);
        convertToImageDto.setAccessible(true);
        verifyPerson = PersonServiceImp.class.getDeclaredMethod("verifyPerson", String.class, String.class, String.class, String.class, String.class);
        verifyPerson.setAccessible(true);
        when(personRepository.findById(person1.getId())).thenReturn(java.util.Optional.of(person1));
        when(personRepository.findById(person2.getId())).thenReturn(java.util.Optional.of(person2));
        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));
        personService = new PersonServiceImp(personRepository);
    }

    @Test
    void convertToImageDto() throws InvocationTargetException, IllegalAccessException {
        PersonImageDto personImageDto = new PersonImageDto(person1.getId(), person1.getName(), person1.getSurname(),
                person1.getPhoneNumber(), person1.getBusinessNumber(), person1.getEmail(), person1.getImage());
        PersonImageDto received = (PersonImageDto) convertToImageDto.invoke(personService, person1);

        assertThat(personImageDto.getId()).isSameAs(received.getId());

    }

    @Test
    void getPeople() throws InvocationTargetException, IllegalAccessException {
        PersonImageDto personImageDto1 = (PersonImageDto) convertToImageDto.invoke(personService, person1);
        PersonImageDto personImageDto2 = (PersonImageDto) convertToImageDto.invoke(personService, person2);
        List<PersonImageDto> received = personService.getPeople();
        assertThat(personImageDto1.getId()).isSameAs(received.get(0).getId());
        assertThat(personImageDto2.getId()).isSameAs(received.get(1).getId());
    }

    @Test
    void getPerson() throws InvocationTargetException, IllegalAccessException {
        PersonImageDto personImageDto1 = (PersonImageDto) convertToImageDto.invoke(personService, person1);
        PersonImageDto received = personService.getPerson(person1.getId());
        assertThat(personImageDto1.getId()).isSameAs(received.getId());
    }

    @Test
    void verify() throws InvocationTargetException, IllegalAccessException {
        boolean failed1 = (Boolean) verifyPerson.invoke(personService, "oleksii", "Bondar", "22222", "22222", "ww@ww.ww");
        boolean failed2 = (Boolean) verifyPerson.invoke(personService, "Oleksii", "bondar", "22222", "22222", "ww@ww.ww");
        boolean failed3 = (Boolean) verifyPerson.invoke(personService, "Oleksii", "Bondar", "222", "22222", "ww@ww.ww");
        boolean failed4 = (Boolean) verifyPerson.invoke(personService, "Oleksii", "Bondar", "22222", "222", "ww@ww.ww");
        boolean failed5 = (Boolean) verifyPerson.invoke(personService, "Oleksii", "Bondar", "22222", "22222", "eeeeeeee");
        boolean correct = (Boolean) verifyPerson.invoke(personService, "Oleksii", "Bondar", "22222", "22222", "ww@ww.ww");
        assertThat(failed1).isSameAs(false);
        assertThat(failed2).isSameAs(false);
        assertThat(failed3).isSameAs(false);
        assertThat(failed4).isSameAs(false);
        assertThat(failed5).isSameAs(false);
        assertThat(correct).isSameAs(true);
    }
}
