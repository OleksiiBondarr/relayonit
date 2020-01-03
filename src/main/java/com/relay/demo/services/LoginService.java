package com.relay.demo.services;

import com.relay.demo.dto.UserPassDto;
import com.relay.demo.entities.User;

public interface LoginService {
    UserPassDto getUser(String login);
    void register(UserPassDto userPassDto);
}
