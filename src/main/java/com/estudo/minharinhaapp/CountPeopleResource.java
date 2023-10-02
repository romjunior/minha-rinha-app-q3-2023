package com.estudo.minharinhaapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/contagem-pessoas")
public class CountPeopleResource {

    private final PeopleRepository repository;

    public CountPeopleResource(final PeopleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Long countPeople() {
        return repository.count();
    }
}
