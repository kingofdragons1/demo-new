package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        if (repository.existsById(id)) {
            person.setId(id);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}
