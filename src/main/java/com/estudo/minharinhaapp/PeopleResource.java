package com.estudo.minharinhaapp;

import jakarta.validation.Valid;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Validated
@RestController
@RequestMapping(path = "/pessoas")
public class PeopleResource {

    private final PeopleRepository repository;

    private final PeopleMapper peopleMapper;

    public PeopleResource(final PeopleRepository repository, final PeopleMapper peopleMapper) {
        this.repository = repository;
        this.peopleMapper = peopleMapper;
    }

    //@Transactional(noRollbackFor = DbActionExecutionException.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPeople(@Valid @RequestBody final People people) {
        validate(people);
        final var uuidId = UUID.randomUUID();
        try {
            final var entity = peopleMapper.toEntity(uuidId, people);
            entity.setSearchText(
                    getSearchText(entity)
            );
            repository.save(entity);
        } catch (DbActionExecutionException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity
                .created(URI.create("/pessoas/" + uuidId))
                .build();
    }

    private static String getSearchText(PeopleEntity entity) {
        var searchText = new StringBuilder();
        if(entity.getSurname() != null) {
            searchText.append(entity.getSurname().toLowerCase());
        }

        if(entity.getName() != null) {
            searchText.append(entity.getName().toLowerCase());
        }

        if (entity.getStack() != null && !entity.getStack().isEmpty()) {
            for (String stackItem : entity.getStack()) {
                searchText.append(stackItem.toLowerCase());
            }
        }

        return searchText.toString();
    }

    private void validate(final People people) {
        validateNullAndSize(people.apelido(), 32);
        validateNullAndSize(people.nome(), 100);
        validateNullAndSize(people.nascimento());
        validateNullAndSize(people.stack(), 32);
    }

    private void validateNullAndSize(final LocalDate nascimento) {
        if(null == nascimento) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private void validateNullAndSize(final String texto, final int size) {
        if(null == texto || texto.length() > size) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private void validateNullAndSize(final List<String> stack, final int size) {
        if(null != stack) {
            for (final String item: stack) {
                if(null == item || item.length() > size) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<People> getPeopleById(@PathVariable("id") final UUID id) {
       return repository.findById(id)
               .map(peopleMapper::toModel)
               .map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<People>> getPeopleByTerm(@RequestParam("t") final String term) {
        /*final var result = repository.findAllBySurnameOrNameOrStackInLimit(term, 50)
                .stream().map(peopleMapper::toModel)
                .toList();*/
        final var searchQuery = new StringBuilder();
        searchQuery.append("%");
        searchQuery.append(term.toLowerCase());
        searchQuery.append("%");
        final var result = repository.findlAllByTermCustomQuery(searchQuery, 50)
                .stream().map(peopleMapper::toModel)
                .toList();
        return ResponseEntity.ok(result);
    }
}
