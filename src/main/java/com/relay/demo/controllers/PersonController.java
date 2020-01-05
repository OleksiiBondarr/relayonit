package com.relay.demo.controllers;

import com.relay.demo.dto.PersonDto;
import com.relay.demo.dto.PersonIdDto;
import com.relay.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class PersonController {
    private final PersonService personService;

    @Autowired
    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("people")
    public String getAddressBook(Model model) {
        model.addAttribute("peopleList", personService.getPeople());
        return "peoplePage";
    }

    @GetMapping("create")
    public String getCreatePerson() {
        return "createPage";
    }

    @PostMapping("create")
    public String createPerson(PersonIdDto personIdDto, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
        personService.postPerson(personIdDto);
        return getAddressBook(model);
    }

    @GetMapping("update")
    public String getUpdatePerson(Integer id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "updatePage";
    }

    @PostMapping("update")
    public String updatePerson(PersonDto personDto, Model model) {
        personService.updatePerson(personDto);
        return getAddressBook(model);
    }

    @GetMapping("delete")
    public String deletePerson(Integer id, Model model) {
        personService.deleteUser(id);
        return getAddressBook(model);
    }
}