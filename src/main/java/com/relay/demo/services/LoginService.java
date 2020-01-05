package com.relay.demo.services;

import com.relay.demo.dto.UserPassDto;

public interface LoginService {
    UserPassDto getUser(String login);
    void register(UserPassDto userPassDto);
}
