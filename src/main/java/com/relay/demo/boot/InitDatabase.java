package com.relay.demo.boot;

import com.relay.demo.entities.User;
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
    private Resource users;

    @Autowired
    public InitDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.users = new ClassPathResource("users");
    }

    @PostConstruct
    public void initDatabase() {
        try {
            InputStream fileUsers = users.getInputStream();
            for (Scanner s = new Scanner(fileUsers); s.hasNextLine(); ) {
                String[] arrOfStr = s.nextLine().split(", ", 2);
                userRepository.save(new User(arrOfStr[0], arrOfStr[1]));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
