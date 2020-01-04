package com.relay.demo.boot;

import com.relay.demo.entities.Person;
import com.relay.demo.entities.User;
import com.relay.demo.repositories.PersonRepository;
import com.relay.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component
public class InitDatabase {
    private UserRepository userRepository;
    private PersonRepository personRepository;
    private Resource users;
    private Resource people;

    @Autowired
    public InitDatabase(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.users = new ClassPathResource("users");
        this.people = new ClassPathResource("people");
    }

    @PostConstruct
    public void initDatabase() {
        try {
            InputStream fileUsers = users.getInputStream();
            for (Scanner s = new Scanner(fileUsers); s.hasNextLine(); ) {
                String[] arrOfStr = s.nextLine().split(", ", 2);
                userRepository.save(new User(arrOfStr[0], arrOfStr[1]));
            }
            InputStream filePeople = people.getInputStream();
            for (Scanner s = new Scanner(filePeople); s.hasNextLine(); ) {
                String[] arrOfStr = s.nextLine().split(", ", 5);
                personRepository.save(new Person(arrOfStr[0], arrOfStr[1],arrOfStr[2], arrOfStr[3], arrOfStr[4]));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
