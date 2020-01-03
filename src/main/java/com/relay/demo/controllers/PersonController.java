package com.relay.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addressBook")
public class PersonController {

    @GetMapping("addressBook")
    public String getAddressBook() {
        System.out.println("ss2");
        return "addressBook";
    }

}
