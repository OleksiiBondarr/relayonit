package com.relay.demo.repositories;

import com.relay.demo.entities.Person;
import com.relay.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
