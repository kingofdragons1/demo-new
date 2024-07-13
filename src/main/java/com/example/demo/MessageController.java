package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @GetMapping
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        if (repository.existsById(id)) {
            message.setId(id);
            return new ResponseEntity<>(repository.save(message), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(repository.save(message), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }
}
