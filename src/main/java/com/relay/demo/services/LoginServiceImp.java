package com.relay.demo.services;

import com.relay.demo.dto.UserPassDto;
import com.relay.demo.entities.User;
import com.relay.demo.exceptions.UserAlreadyExistException;
import com.relay.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{
    private final UserRepository repository;
    @Autowired
    public LoginServiceImp(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserPassDto getUser(String login) {
        if (repository.findById(login).isPresent()){
            User user = repository.findById(login).get();
            return new UserPassDto(user.getLogin(),user.getPassword());
        }
        else{
            return null;
        }
    }

    @Override
    public void register(UserPassDto userPassDto) {
    if (!repository.findById(userPassDto.getLogin()).isPresent()){
            User user = new User(userPassDto.getLogin(),userPassDto.getPassword());
            repository.save(user);
        }else {
            throw new UserAlreadyExistException(userPassDto.getLogin());
        }
    }
}
