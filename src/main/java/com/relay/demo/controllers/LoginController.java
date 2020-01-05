package com.relay.demo.controllers;

import com.relay.demo.dto.UserPassDto;
import com.relay.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {

    private final LoginService loginService;

    @Autowired
    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("login")
    public String loginPage(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (error == null) {
            return "loginPage";
        } else {
            if (error)
                model.addAttribute("error", true);
            return "loginPage";
        }
    }

    @GetMapping("register")
    public String Register() {
        return "registerPage";
    }

    @PostMapping("register")
    public String CreateUser(UserPassDto userPassDto) {
        loginService.register(userPassDto);
        return "loginPage";
    }

}
